package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HiddenFormFieldStorageStrategy extends ContextAppCaptchaCodeStorageStrategy {
    private static final Logger log = LogManager.getLogger(HiddenFormFieldStorageStrategy.class);

    public HiddenFormFieldStorageStrategy(CaptchaCodeContainer captchaCodeContainer) {
        super(captchaCodeContainer);
    }

    @Override
    protected void saveId(HttpServletRequest request, HttpServletResponse response, Integer captchaId) {
        HttpSession session = request.getSession();

        session.setAttribute(ShopLiterals.CAPTCHA_ID, captchaId);

        // set storage timeout on captcha id
        Executors.newSingleThreadScheduledExecutor().schedule(() -> session.removeAttribute(ShopLiterals.CAPTCHA_ID),
                TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public String getStoredCode(HttpServletRequest request) {
        Integer captchaId = (Integer) request.getSession().getAttribute(ShopLiterals.CAPTCHA_ID);
        log.debug("Stored captcha id: " + captchaId);
        return captchaCodeContainer.getContainer().get(captchaId);
    }

    @Override
    public String getHtml(HttpServletRequest request) {
        return "<img src='captcha' /><input name='captchaId' value='"
                + request.getSession().getAttribute(ShopLiterals.CAPTCHA_ID) + "' type='hidden'>";
    }
}
