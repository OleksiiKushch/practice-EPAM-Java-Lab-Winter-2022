package com.epam.task14.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.User;
import com.epam.task11.service.ServiceException;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task12.service.transaction.impl.MySqlTransactionManager;
import com.epam.task14.db.dao.impl.OrderDaoImpl;
import com.epam.task14.entity.Order;
import com.epam.task14.service.OrderService;
import com.epam.task14.service.impl.OrderServiceImpl;
import com.epam.task14.util.Cart;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CheckoutServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User loggedUser = (User) session.getAttribute(ShopLiterals.LOGGED_USER);
        if (Objects.isNull(loggedUser)) {
            LOG.debug("Redirect to login page, because user not logged.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        LOG.debug(loggedUser.toStringWithoutSensitiveData());

        Cart cart = (Cart) session.getAttribute(ShopLiterals.CART);
        if (Objects.isNull(cart)) {
            cart = new Cart();
        }
        LOG.debug(cart);

        String delivery = request.getParameter(ShopLiterals.DELIVERY);

        Order newOrder = new Order(delivery, loggedUser, cart);
        LOG.debug("Order before insert: " + newOrder);

        List<String> errors = new ArrayList<>();
        ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance(new OrderDaoImpl(connectionBuilder), new MySqlTransactionManager(connectionBuilder));
        try {
            orderService.checkout(newOrder);
        } catch (ServiceException exception) {
            errors.add(exception.getMessage());
            LOG.error(exception.getMessage());
        }

        if (errors.isEmpty()) {
            cart.clean();
            session.setAttribute(ShopLiterals.CART, cart);
            request.getRequestDispatcher("/WEB-INF/view/jsp/general/main.jsp").forward(request, response);
        } else {
            session.setAttribute(ShopLiterals.ERRORS, errors);
            response.sendRedirect(request.getContextPath() + "/my_cart");
        }
    }
}
