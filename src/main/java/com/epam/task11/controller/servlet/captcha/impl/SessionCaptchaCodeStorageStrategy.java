package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaCodeStorageStrategy;
import com.epam.task11.util.captcha.GeneratorCode;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SessionCaptchaCodeStorageStrategy implements CaptchaCodeStorageStrategy {
    private static final Logger log = LogManager.getLogger(SessionCaptchaCodeStorageStrategy.class);

    private GeneratorCode generatorCode;

    public SessionCaptchaCodeStorageStrategy(GeneratorCode generatorCode) {
        this.generatorCode = generatorCode;
    }

    public GeneratorCode getGeneratorCode() {
        return generatorCode;
    }

    public void setGeneratorCode(GeneratorCode generatorCode) {
        this.generatorCode = generatorCode;
    }

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        // generate captcha code
        String code = generatorCode.generate();
        log.debug("Generated captcha code: " + code);

        // save captcha code
        session.setAttribute(ShopLiterals.CAPTCHA_CODE, code);

        // set storage timeout on captcha code
        Executors.newSingleThreadScheduledExecutor().schedule(() -> session.removeAttribute(ShopLiterals.CAPTCHA_CODE),
                TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public String getStoredCode(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(ShopLiterals.CAPTCHA_CODE);
    }

    @Override
    public String getHtml(HttpServletRequest request) {
        return "<img src='captcha' />";
    }
}
