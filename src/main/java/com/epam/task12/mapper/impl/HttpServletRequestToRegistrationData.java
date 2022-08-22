package com.epam.task12.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.util.RegistrationData;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Oleksii Kushch
 */
public class HttpServletRequestToRegistrationData implements Mapper<HttpServletRequest, RegistrationData> {
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
    }
}
