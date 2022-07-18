package com.epam.task11.controller.servlet.captcha;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reloadCaptcha")
public class ReloadCaptchaServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ReloadCaptchaServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Captcha is reloaded.");
        request.getRequestDispatcher("/WEB-INF/view/jsp/component/reload_captcha.jsp").forward(request, response);
    }
}
