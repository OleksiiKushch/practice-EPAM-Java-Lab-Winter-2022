package com.epam.task11.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.ContextListener;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.entity.User;
import com.epam.task11.service.MyServiceException;
import com.epam.task11.util.RegistrationData;
import com.epam.task11.validation.impl.RegistrationDataValidator;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.connection.impl.PoolConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlUserDao;
import com.epam.task12.service.UserService;
import com.epam.task12.service.impl.UserServiceImpl;
import com.epam.task12.service.transaction.impl.TransactionManagerImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
@WebServlet("/registration")
@MultipartConfig(
        location= ContextListener.ABSOLUTE_PATH_STORAGE_AVATARS,
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize       = 1024 * 1024 * 15, // 15 MB
        maxRequestSize    = 1024 * 1024 * 25  // 25 MB
)
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegistrationServlet.class);

    public static final String SAVED_AVATAR_EXTENSION = ".png";

    private static final int SIZE_EMPTY_PART = 0;

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
        log.info("User try registration");
        RegistrationData registrationData = getDataFromRequest(request);

        CaptchaDataStorageStrategy captchaDataStorageStrategy = (CaptchaDataStorageStrategy) request.getServletContext()
                .getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY);

        registrationData.setCaptchaLifetime(captchaDataStorageStrategy.getCaptchaLifetime(request));
        log.debug("Registration data: " + registrationData.toStringWithoutSensitiveData());

        String storedCaptchaCode = captchaDataStorageStrategy.getStoredCode(request);
        log.debug("Stored captcha code: " + storedCaptchaCode);

        captchaDataStorageStrategy.cleanStoredData(request);

        List<String> errors = new RegistrationDataValidator(storedCaptchaCode, captchaDataStorageStrategy.getCaptchaTimeout()).isValid(registrationData);
        if (errors.isEmpty()) {
            User user = registrationData.mapUser();
            log.debug("New user data: " + user.toStringWithoutSensitiveData());
            try {
                // new UserServiceImpl(new UserRepositoryMockImpl()).create(user);
                ConnectionBuilder connectionBuilder = PoolConnectionBuilder.getInstance();
                UserService userService = new UserServiceImpl(new MySqlUserDao(connectionBuilder), new TransactionManagerImpl(connectionBuilder));
                userService.registration(user);
                saveAvatarImage(request, user);
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

    private void saveAvatarImage(HttpServletRequest request, User user) {
        String nameSavedFile = user.getEmail();
        try {
            Part avatar = request.getPart(ShopLiterals.AVATAR_IMAGE);
            if (avatar.getSize() > SIZE_EMPTY_PART) {
                avatar.write(nameSavedFile + SAVED_AVATAR_EXTENSION);
                log.info("User: " + user.getEmail() + " successfully set an avatar");
            } else {
                log.info("User: " + user.getEmail() + " didn't set an avatar");
            }
        } catch (IOException | ServletException exception) {
            log.warn(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
