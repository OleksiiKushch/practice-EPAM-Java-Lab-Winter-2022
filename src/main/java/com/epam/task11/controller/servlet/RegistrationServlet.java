package com.epam.task11.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.ContextListener;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.service.ServiceException;
import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.impl.RegistrationDataValidator;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlUserDao;
import com.epam.task12.mapper.impl.HttpServletRequestToRegistrationData;
import com.epam.task12.service.UserService;
import com.epam.task12.service.impl.UserServiceImpl;
import com.epam.task12.service.transaction.impl.MySqlTransactionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
@WebServlet("/registration")
@MultipartConfig(
        location= ContextListener.ABSOLUTE_PATH_STORAGE_USER_AVATARS,
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize       = 1024 * 1024 * 15, // 15 MB
        maxRequestSize    = 1024 * 1024 * 25  // 25 MB
)
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegistrationServlet.class);

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        RegistrationData registrationData = (RegistrationData) session.getAttribute(ShopLiterals.REGISTRATION_DATA);
        log.debug("Registration data: " + (registrationData == null ? null : registrationData.toStringWithoutSensitiveData()));
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
        RegistrationData registrationData = new RegistrationData();
        new HttpServletRequestToRegistrationData().map(request, registrationData);
        log.debug("Registration data: " + registrationData.toStringWithoutSensitiveData());

        CaptchaDataStorageStrategy captchaDataStorageStrategy = (CaptchaDataStorageStrategy) request.getServletContext()
                .getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY);

        String storedCaptchaCode = captchaDataStorageStrategy.getStoredCode(request);
        log.debug("Stored captcha code: " + storedCaptchaCode);

        captchaDataStorageStrategy.cleanStoredData(request);

        List<String> errors = new RegistrationDataValidator(storedCaptchaCode, captchaDataStorageStrategy.getCaptchaTimeout()).isValid(registrationData);
        if (errors.isEmpty()) {
            try {
                // new UserServiceImpl(new UserRepositoryMockImpl()).create(user);
                ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();
                UserService userService = UserServiceImpl.getInstance(new MySqlUserDao(connectionBuilder), new MySqlTransactionManager(connectionBuilder));
                userService.registration(registrationData);
            } catch (ServiceException exception) {
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
            HttpSession session = request.getSession();
            session.setAttribute(ShopLiterals.REGISTRATION_DATA, registrationData);
            session.setAttribute(ShopLiterals.ERRORS, errors);
            response.sendRedirect(request.getContextPath() + "/registration");
        }
    }

    private void cleanPasswords(RegistrationData registrationData) {
        registrationData.setPassword(null);
        registrationData.setConfirmationPassword(null);
    }
}
