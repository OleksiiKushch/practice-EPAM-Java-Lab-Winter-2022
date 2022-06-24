package com.epam.task11.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.User;
import com.epam.task11.service.MyServiceException;
import com.epam.task11.service.impl.UserServiceImpl;
import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.impl.RegistrationDataValidator;
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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegistrationServlet.class);

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        RegistrationData registrationData = (RegistrationData) session.getAttribute(ShopLiterals.REGISTRATION_DATA);
        log.debug("Registration data: " + registrationData);
        request.setAttribute(ShopLiterals.REGISTRATION_DATA, registrationData);
        session.removeAttribute(ShopLiterals.REGISTRATION_DATA);

        List<String> errors = (List<String>) session.getAttribute(ShopLiterals.ERRORS);
        log.debug("Error messages: " + errors);
        request.setAttribute(ShopLiterals.ERRORS, errors);
        session.removeAttribute(ShopLiterals.ERRORS);

        request.getRequestDispatcher("/WEB-INF/view/jsp/general/registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("User try registration");
        RegistrationData registrationData = getDataFromRequest(request);
        log.debug("Registration data: " + registrationData);
        String captchaCode = (String) request.getSession().getAttribute(ShopLiterals.CAPTCHA_CODE);
        log.debug("Captcha code: " + captchaCode);
        List<String> errors = new RegistrationDataValidator(captchaCode).isValid(registrationData);
        if (errors.isEmpty()) {
            User user = registrationData.mapUser();
            log.debug("New user data: " + user);
            try {
                new UserServiceImpl().create(user);
            } catch (MyServiceException exception) {
                errors.add(exception.getMessage());
                log.error(exception.getMessage());
            }
        }
        if (errors.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/main");
            log.info("New user successful registration!");
        } else {
            log.debug("Registration data is invalid, error messages: " + errors);
            cleanPasswords(registrationData);
            log.debug("Registration data after clean password: " + registrationData);
            HttpSession session = request.getSession();
            session.setAttribute(ShopLiterals.REGISTRATION_DATA, registrationData);
            session.setAttribute(ShopLiterals.ERRORS, errors);
            response.sendRedirect(request.getContextPath() + "/registration");
        }
    }

    private RegistrationData getDataFromRequest(HttpServletRequest request) {
        List<String> parameters = new ArrayList<>();
        for (String parameter : RegistrationData.getListStrParameters()) {
            parameters.add(request.getParameter(parameter));
        }
        return RegistrationData.mapRegistrationData(parameters);
    }

    private void cleanPasswords(RegistrationData registrationData) {
        registrationData.setPassword(null);
        registrationData.setConfirmationPassword(null);
    }
}
