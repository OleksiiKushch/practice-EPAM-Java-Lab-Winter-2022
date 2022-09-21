package com.epam.task14.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
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
import java.util.List;
import java.util.Objects;

@WebServlet("/my_cart")
public class MyCartServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(MyCartServlet.class);

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (Objects.isNull(session.getAttribute(ShopLiterals.CART))) {
            session.setAttribute(ShopLiterals.CART, new Cart());
        }

        List<String> errors = (List<String>) session.getAttribute(ShopLiterals.ERRORS);
        LOG.debug("Error messages: " + errors);
        request.setAttribute(ShopLiterals.ERRORS, errors);
        session.removeAttribute(ShopLiterals.ERRORS);

        request.getRequestDispatcher("/WEB-INF/view/jsp/general/my_cart.jsp").forward(request, response);
    }
}
