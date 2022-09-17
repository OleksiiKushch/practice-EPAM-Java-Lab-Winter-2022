package com.epam.task16.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task14.controller.servlet.MyCartServlet;
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

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(MyCartServlet.class);

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<String> errors = (List<String>) session.getAttribute(ShopLiterals.ERRORS);
        LOG.debug("Error messages: " + errors);
        request.setAttribute(ShopLiterals.ERRORS, errors);
        session.removeAttribute(ShopLiterals.ERRORS);

        request.getRequestDispatcher("/WEB-INF/view/jsp/general/default_error_page.jsp").forward(request, response);
    }
}
