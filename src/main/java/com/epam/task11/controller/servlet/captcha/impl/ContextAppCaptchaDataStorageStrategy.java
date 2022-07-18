package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Partial implementation that stores captcha data on the client side, for example: cookie or hidden form field.
 *
 * Noted: This implementation uses a container ({@link CaptchaCodeContainer}) that contains the captcha id as a key and the captcha value itself as a value.
 * This container must be stored in the application context (servlet context).
 *
 * @author Oleksii Kushch
 */
public abstract class ContextAppCaptchaDataStorageStrategy extends CaptchaDataStorageStrategy {
    private static final Logger log = LogManager.getLogger(ContextAppCaptchaDataStorageStrategy.class);

    protected CaptchaCodeContainer captchaCodeContainer;

    public ContextAppCaptchaDataStorageStrategy(CaptchaCodeContainer captchaCodeContainer, int captchaTimeout) {
        super(captchaTimeout);
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
