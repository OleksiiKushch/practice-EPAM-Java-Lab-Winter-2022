package com.epam.task11.controller;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.impl.CookieStorageStrategy;
import com.epam.task11.controller.servlet.captcha.impl.HiddenFormFieldStorageStrategy;
import com.epam.task11.controller.servlet.captcha.impl.SessionCaptchaDataStorageStrategy;
import com.epam.task11.util.captcha.CaptchaCodeContainer;
import com.epam.task11.util.captcha.GeneratorRandomFourDigitNumCode;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
@WebListener("application context listener")
public class ContextListener implements ServletContextListener {
    public static final String ABSOLUTE_PATH_STORAGE_USER_AVATARS = "C:/javaTech/tomcat-9/data/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/user/avatars/";
    public static final String ABSOLUTE_PATH_STORAGE_PRODUCT_PICTURES = "C:/javaTech/tomcat-9/data/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/product/pictures/";

    public static final String PATH_DEFAULT_PRODUCT_PICTURE = "media/product/";
    public static final String PATH_DEFAULT_USER_AVATAR = "media/user/";

    public static final String WELCOME_SERVLET_NAME = "main";
    public static final String WELCOME_SERVLET_PATH = "//WEB-INF/view/jsp/general/main.jsp";

    /**
     * Initialize log4j and some app settings when the application is being started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        initLog4j(context);

        initCaptchaDataStorageStrategy(context);

        initSecurity(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    private void initLog4j(ServletContext context) {
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
    }

    private void initCaptchaDataStorageStrategy(ServletContext context) {
        CaptchaCodeContainer captchaCodeContainer = new CaptchaCodeContainer();
        initCaptchaCodeContainer(captchaCodeContainer);

        String captchaDataStorageStrategy = context.getInitParameter("captcha-data-stored-strategy");
        int captchaTimeout = Integer.parseInt(context.getInitParameter("captcha-timeout"));

        if ("session".equals(captchaDataStorageStrategy)) {
            context.setAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY,
                    new SessionCaptchaDataStorageStrategy(new GeneratorRandomFourDigitNumCode(), captchaTimeout));
        } else if ("hidden_field".equals(captchaDataStorageStrategy)) {
            context.setAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY,
                    new HiddenFormFieldStorageStrategy(captchaCodeContainer, captchaTimeout));
        } else if ("cookie".equals(captchaDataStorageStrategy)) {
            context.setAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY,
                    new CookieStorageStrategy(captchaCodeContainer, captchaTimeout));
        }
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

    private void initSecurity(ServletContext context) {
        String pathSecurityConfigFile = context.getInitParameter("security-config-location");
        String fullPathSecurityConfigFile = context.getRealPath("") + File.separator + pathSecurityConfigFile;
        Map<String, String> securedPages = parseSecureConfigFile(fullPathSecurityConfigFile);
        context.setAttribute(ShopLiterals.SECURED_PAGES, securedPages);
    }

    private Map<String, String> parseSecureConfigFile(String fullPathSecurityConfigFile) {
        Map<String, String> result = new HashMap<>();
        // parse file
        result.put("/admin", "admin");
        return result;
    }
}

