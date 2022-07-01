package com.epam.task11.controller;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.impl.HiddenFormFieldStorageStrategy;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    /**
     * Initialize log4j when the application is being started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        // initialize log4j here
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);

        // initialize captcha code container
        CaptchaCodeContainer captchaCodeContainer = new CaptchaCodeContainer();
        initCaptchaCodeContainer(captchaCodeContainer);

        // initialize captcha code storage strategy

//        context.setAttribute(ShopLiterals.CAPTCHA_CODE_STORAGE_STRATEGY,
//                new SessionCaptchaCodeStorageStrategy(new GeneratorRandomFourDigitNumCode()));

        context.setAttribute(ShopLiterals.CAPTCHA_CODE_STORAGE_STRATEGY,
                new HiddenFormFieldStorageStrategy(captchaCodeContainer));

//        context.setAttribute(ShopLiterals.CAPTCHA_CODE_STORAGE_STRATEGY,
//                new CookieStorageStrategy(captchaCodeContainer));
    }

    private void initCaptchaCodeContainer(CaptchaCodeContainer captchaCodeContainer) {
        Map<Integer, String> container = new HashMap<>();
        container.put(1, "1111");
        container.put(2, "2222");
        container.put(3, "3333");
        container.put(4, "4444");
        container.put(5, "5555");
        container.put(6, "6666");
        container.put(7, "7777");
        container.put(8, "8888");
        container.put(9, "9999");
        captchaCodeContainer.setContainer(container);
    }
}

