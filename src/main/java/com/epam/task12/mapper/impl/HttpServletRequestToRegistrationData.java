package com.epam.task12.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.util.RegistrationData;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task12.util.MyMediaUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Oleksii Kushch
 */
public class HttpServletRequestToRegistrationData implements Mapper<HttpServletRequest, RegistrationData> {
    private static final Logger LOG = LogManager.getLogger(HttpServletRequestToRegistrationData.class);

    @Override
    public void map(HttpServletRequest request, RegistrationData registrationData) throws MapException {
        registrationData.setEmail(request.getParameter(ShopLiterals.EMAIL));
        registrationData.setFirstName(request.getParameter(ShopLiterals.FIRST_NAME));
        registrationData.setLastName(request.getParameter(ShopLiterals.LAST_NAME));
        registrationData.setPassword(request.getParameter(ShopLiterals.PASSWORD));
        registrationData.setConfirmationPassword(request.getParameter(ShopLiterals.CONFIRMATION_PASSWORD));
        registrationData.setCaptchaCode(request.getParameter(ShopLiterals.CAPTCHA));
        registrationData.setCaptchaLifetime(((CaptchaDataStorageStrategy) request.getServletContext()
                .getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY)).getCaptchaLifetime(request));
        try {
            registrationData.setAvatar(request.getPart(ShopLiterals.AVATAR_IMAGE));
        } catch (IOException | ServletException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
        registrationData.setBase64Avatar(MyMediaUtils.convertToBase64(registrationData.getAvatar()));
    }
}
