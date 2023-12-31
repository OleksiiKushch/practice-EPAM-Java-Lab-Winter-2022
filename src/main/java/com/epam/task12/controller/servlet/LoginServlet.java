package com.epam.task12.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.user.User;
import com.epam.task11.service.ServiceException;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlUserDao;
import com.epam.task12.mapper.impl.HttpServletRequestToLoginData;
import com.epam.task12.service.UserService;
import com.epam.task12.service.impl.UserServiceImpl;
import com.epam.task12.service.transaction.impl.MySqlTransactionManager;
import com.epam.task12.util.LoginData;
import com.epam.task12.validation.impl.LoginDataValidator;
import com.epam.task16.db.impl.mysql.MySqlUserRoleDao;
import com.epam.task16.service.UserRoleService;
import com.epam.task16.service.impl.UserRoleServiceImpl;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        LoginData loginData = (LoginData) session.getAttribute(ShopLiterals.LOGIN_DATA);
        request.setAttribute(ShopLiterals.LOGIN_DATA, loginData);
        session.removeAttribute(ShopLiterals.LOGIN_DATA);

        List<String> errors = (List<String>) session.getAttribute(ShopLiterals.ERRORS);
        LOG.debug("Error messages: " + errors);
        request.setAttribute(ShopLiterals.ERRORS, errors);
        session.removeAttribute(ShopLiterals.ERRORS);

        request.getRequestDispatcher("/WEB-INF/view/jsp/general/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginData loginData = new LoginData();
        new HttpServletRequestToLoginData().map(request, loginData);

        List<String> errors = new LoginDataValidator().isValid(loginData);
        if (errors.isEmpty()) {
            ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();
            UserRoleService userRoleService = new UserRoleServiceImpl(new MySqlUserRoleDao(connectionBuilder));
            UserService userService = UserServiceImpl.getInstance(new MySqlUserDao(connectionBuilder), new MySqlTransactionManager(connectionBuilder), userRoleService);
            try {
                User user = userService.login(loginData);
                request.getSession().setAttribute(ShopLiterals.LOGGED_USER, user);
                LOG.info("User: " + user.getEmail() + " successful log in!");
            } catch (ServiceException exception) {
                errors.add(exception.getMessage());
                LOG.error(exception.getMessage());
            }
        }
        if (errors.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/main");
        } else {
            LOG.debug("Login data is invalid, error messages: " + errors);
            cleanPassword(loginData);
            HttpSession session = request.getSession();
            session.setAttribute(ShopLiterals.LOGIN_DATA, loginData);
            session.setAttribute(ShopLiterals.ERRORS, errors);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private void cleanPassword(LoginData loginData) {
        loginData.setPassword(null);
    }
}
