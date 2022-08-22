package com.epam.task12.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LogoutServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(ShopLiterals.LOGGED_USER);
        clearAllSessionAttributes(request.getSession());
        LOG.info("User: " + user.getEmail() + " logout!");
        response.sendRedirect(request.getContextPath() + "/main");
    }

    private void clearAllSessionAttributes(HttpSession session) {
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        while(sessionAttributes.hasMoreElements()){
            session.setAttribute(sessionAttributes.nextElement(),null);
        }
    }
}
