package com.epam.task11.controller.servlet.captcha.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Implementation that stores captcha data in hidden form field on html/jsp page.
 *
 * @author Oleksii Kushch
 */
public class HiddenFormFieldStorageStrategy extends ContextAppCaptchaDataStorageStrategy {
    private static final Logger log = LogManager.getLogger(HiddenFormFieldStorageStrategy.class);

    public HiddenFormFieldStorageStrategy(CaptchaCodeContainer captchaCodeContainer, int captchaTimeout) {
        super(captchaCodeContainer, captchaTimeout);
    }

    @Override
    protected void saveId(HttpServletRequest request, HttpServletResponse response, Integer captchaId) {
        request.setAttribute(ShopLiterals.CAPTCHA_ID, captchaId);
        saveCaptchaLoadingTime(request);
    }

    @Override
    public String getStoredCode(HttpServletRequest request) {
        Integer captchaId = Integer.valueOf(request.getParameter(ShopLiterals.CAPTCHA_ID));
        log.debug("Stored captcha id: " + captchaId);
        return captchaCodeContainer.getContainer().get(captchaId);
    }

    @Override
    public void cleanStoredData(HttpServletRequest request) {
        cleanSavedCaptchaLoadingTime(request);
    }

    @Override
    public String getHtml(HttpServletRequest request) {
        Integer captchaId = (Integer) request.getAttribute(ShopLiterals.CAPTCHA_ID);
        return "<img src='captchaProvider?" + ShopLiterals.CAPTCHA_ID + "=" + captchaId +
                "&t=" + new Date().getTime() + "' />" +
                "<input name='" + ShopLiterals.CAPTCHA_ID + "' value='" + captchaId + "' type='hidden'>";
    }
}
