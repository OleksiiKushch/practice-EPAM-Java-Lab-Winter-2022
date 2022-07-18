package com.epam.task11.controller.servlet.captcha;

import com.epam.task11.constant.ShopLiterals;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The strategy that determines how captcha data will be stored, respectively determines how it will be: save / get / clean (remove).
 * It can also include generating/selecting a random captcha code.
 *
 * Noted: Captcha data, such as the captcha loading time, by default is stored in the session but can be overridden in concrete implementations.
 *
 * @author Oleksii Kushch
 */
public abstract class CaptchaDataStorageStrategy {
    private static final Logger log = LogManager.getLogger(CaptchaDataStorageStrategy.class);

    private static final int MILLIS_TO_SECOND = 1000;

    /** seconds */
    protected int captchaTimeout;

    protected CaptchaDataStorageStrategy(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }

    public int getCaptchaTimeout() {
        return captchaTimeout;
    }

    public void setCaptchaTimeout(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }

    /**
     * The main task is to save the captcha code or its indirect signs, such as the captcha id.
     * It also includes generating/selecting a random captcha code.
     *
     * Noted: must include a call to the {@link #saveCaptchaLoadingTime(HttpServletRequest)} method.
     */
    public abstract void save(HttpServletRequest request, HttpServletResponse response);

    /**
     * @return the stored captcha code
     */
    public abstract String getStoredCode(HttpServletRequest request);

    /**
     * The main task is to save the captcha code or its indirect signs, such as the captcha id.
     * Noted: must include a call to the {@link #cleanSavedCaptchaLoadingTime(HttpServletRequest)} method.
     */
    public abstract void cleanStoredData(HttpServletRequest request);

    /**
     * Return html code which will be inserted in the jsp page.
     * Mainly used to store client-side captcha information in hidden form fields, such as captcha id.
     */
    public abstract String getHtml(HttpServletRequest request);

    /**
     * @return the captcha lifetime in seconds
     */
    public int getCaptchaLifetime(HttpServletRequest request) {
        return (int) ((System.currentTimeMillis() - (long) request.getSession().getAttribute(ShopLiterals.TIME_CAPTCHA_LOAD)) / MILLIS_TO_SECOND);
    }

    protected void saveCaptchaLoadingTime(HttpServletRequest request) {
        Long captchaLoadTime = System.currentTimeMillis();
        request.getSession().setAttribute(ShopLiterals.TIME_CAPTCHA_LOAD, captchaLoadTime);
        log.debug("Save captcha loading time: " + captchaLoadTime);
    }

    protected void cleanSavedCaptchaLoadingTime(HttpServletRequest request) {
        request.getSession().removeAttribute(ShopLiterals.TIME_CAPTCHA_LOAD);
        log.debug("Clean saved captcha loading time");
    }
}
