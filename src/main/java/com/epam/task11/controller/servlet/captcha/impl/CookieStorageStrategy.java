package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieStorageStrategy extends ContextAppCaptchaCodeStorageStrategy {
    private static final Logger log = LogManager.getLogger(CookieStorageStrategy.class);

    public CookieStorageStrategy(CaptchaCodeContainer captchaCodeContainer) {
        super(captchaCodeContainer);
    }

    @Override
    protected void saveId(HttpServletRequest request, HttpServletResponse response, Integer captchaId) {
        Cookie cookie = new Cookie(ShopLiterals.CAPTCHA_ID, String.valueOf(captchaId));
        cookie.setMaxAge(TIMEOUT); // set storage timeout on captcha id
        response.addCookie(cookie);
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
    public String getHtml(HttpServletRequest request) {
        return "<img src='captcha' />";
    }
}
