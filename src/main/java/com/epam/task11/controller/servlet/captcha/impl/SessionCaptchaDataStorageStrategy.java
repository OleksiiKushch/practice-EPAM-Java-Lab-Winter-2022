package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import com.epam.task11.util.captcha.GeneratorCode;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Implementation captcha data storage strategy that stored data (captcha code) in session.
 * The process of generating a random captcha code by a specific implementation on the interface {@link GeneratorCode}.
 *
 * @author Oleksii Kushch
 */
public class SessionCaptchaDataStorageStrategy extends CaptchaDataStorageStrategy {
    private static final Logger log = LogManager.getLogger(SessionCaptchaDataStorageStrategy.class);

    private GeneratorCode generatorCode;

    public SessionCaptchaDataStorageStrategy(GeneratorCode generatorCode, int captchaTimeout) {
        super(captchaTimeout);
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
        log.debug("Save captcha code: " + code);

        saveCaptchaLoadingTime(request);
    }

    @Override
    public String getStoredCode(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(ShopLiterals.CAPTCHA_CODE);
    }

    @Override
    public void cleanStoredData(HttpServletRequest request) {
        request.getSession().removeAttribute(ShopLiterals.CAPTCHA_CODE);
        cleanSavedCaptchaLoadingTime(request);
    }

    @Override
    public String getHtml(HttpServletRequest request) {
        return "<img src='captchaProvider?t=" + new Date().getTime() + "' />";
    }
}
