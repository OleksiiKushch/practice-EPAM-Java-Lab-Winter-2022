package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Implementation that stores captcha data in cookie.
 *
 * @author Oleksii Kushch
 */
public class CookieStorageStrategy extends ContextAppCaptchaDataStorageStrategy {
    private static final Logger log = LogManager.getLogger(CookieStorageStrategy.class);

    public CookieStorageStrategy(CaptchaCodeContainer captchaCodeContainer, int captchaTimeout) {
        super(captchaCodeContainer, captchaTimeout);
    }

    @Override
    protected void saveId(HttpServletRequest request, HttpServletResponse response, Integer captchaId) {
        Cookie cookie = new Cookie(ShopLiterals.CAPTCHA_ID, String.valueOf(captchaId));
        cookie.setMaxAge(captchaTimeout); // set storage timeout on captcha id
        response.addCookie(cookie);
        saveCaptchaLoadingTime(request);
    }

    @Override
    public String getStoredCode(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> ShopLiterals.CAPTCHA_ID.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny()
                .map(s -> captchaCodeContainer.getContainer().get(Integer.valueOf(s)))
                .orElse(null);
    }

    @Override
    public void cleanStoredData(HttpServletRequest request) {
        cleanSavedCaptchaLoadingTime(request);
    }

    @Override
    public String getHtml(HttpServletRequest request) {
        return "<img src='captcha' />";
    }
}
