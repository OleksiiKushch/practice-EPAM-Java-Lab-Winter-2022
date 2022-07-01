package com.epam.task11.controller.servlet.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaCodeStorageStrategy {
    /** seconds */
    int TIMEOUT = 20;
    void save(HttpServletRequest request, HttpServletResponse response);
    String getStoredCode(HttpServletRequest request);
    String getHtml(HttpServletRequest request);
}
