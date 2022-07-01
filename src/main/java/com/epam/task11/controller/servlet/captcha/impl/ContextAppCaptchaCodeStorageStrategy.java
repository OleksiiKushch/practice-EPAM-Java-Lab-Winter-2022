package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.controller.servlet.captcha.CaptchaCodeStorageStrategy;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ContextAppCaptchaCodeStorageStrategy implements CaptchaCodeStorageStrategy {
    private static final Logger log = LogManager.getLogger(ContextAppCaptchaCodeStorageStrategy.class);

    protected CaptchaCodeContainer captchaCodeContainer;

    public ContextAppCaptchaCodeStorageStrategy(CaptchaCodeContainer captchaCodeContainer) {
        this.captchaCodeContainer = captchaCodeContainer;
    }

    public CaptchaCodeContainer getCaptchaCodeContainer() {
        return captchaCodeContainer;
    }

    public void setCaptchaCodeContainer(CaptchaCodeContainer captchaCodeContainer) {
        this.captchaCodeContainer = captchaCodeContainer;
    }

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Integer captchaId = captchaCodeContainer.getRandomCaptchaId();
        log.debug("Get random captcha id: " + captchaId);
        saveId(request, response, captchaId);
    }

    /**
     * save captcha id
     */
    protected abstract void saveId(HttpServletRequest request, HttpServletResponse response, Integer captchaId);
}
