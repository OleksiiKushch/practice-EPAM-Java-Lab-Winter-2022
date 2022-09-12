package com.epam.task15.controller.filter;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.ContextListener;
import com.epam.task15.localization.storage_strategy.LocalizationStorageStrategy;
import com.epam.task15.localization.storage_strategy.impl.CookieLocalizationStorageStrategy;
import com.epam.task15.localization.storage_strategy.impl.SessionLocalizationStorageStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

public class LocalizationFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(LocalizationFilter.class);

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String localesFilePath = filterConfig.getInitParameter("locales");
        Properties allAvailableLocales = setAllLocalesToRequest(localesFilePath, servletRequest);

        String paramLocalizationStorageStrategy = filterConfig.getInitParameter("localization-storage-strategy");
        LocalizationStorageStrategy localizationStorageStrategy;
        if ("cookie".equals(paramLocalizationStorageStrategy)) {
            int lifetime = Integer.parseInt(servletRequest.getServletContext().getInitParameter("localization-cookie-lifetime"));
            localizationStorageStrategy = new CookieLocalizationStorageStrategy(lifetime);
            LOG.debug("Using cookie localization storage strategy.");
        } else { //  if ("session".equals(paramLocalizationStorageStrategy))
            localizationStorageStrategy = new SessionLocalizationStorageStrategy();
            LOG.debug("Using session localization storage strategy.");
        }
        setLocalizationStorageStrategyToRequest(localizationStorageStrategy, servletRequest);

        String locale = servletRequest.getParameter(ShopLiterals.LOCALE); // for changing current locale in storage
        if (StringUtils.isNotEmpty(locale)) {
            LOG.debug("Request to change locale to: " + locale);
            localizationStorageStrategy.saveLocale(locale, request, response);
        } else {
            locale = localizationStorageStrategy.getLocale(request);
            LOG.debug("Set (update) current locale: " + locale);
            if (StringUtils.isEmpty(locale)) { // reset lifetime for current locale if present in storage or set available locales for browser or set default locale
                Enumeration<Locale> localesFroBrowser = servletRequest.getLocales();
                if (localesFroBrowser.hasMoreElements()) {
                    locale = findFirstMatchWithBrowserLocale(localesFroBrowser, allAvailableLocales);
                }
            }
            if (StringUtils.isEmpty(locale)) {
                locale = filterConfig.getInitParameter("default-locale");
                LOG.debug("Set default locale: " + locale);
            }
            localizationStorageStrategy.saveLocale(locale, request, response);
        }
        LOG.debug("Set locale: " + locale);

        String servletPath = request.getServletPath();
        if (StringUtils.isNotEmpty(servletPath)) {
            String servletName;
            if (ContextListener.WELCOME_SERVLET_PATH.equals(servletPath)) {
                servletName = ContextListener.WELCOME_SERVLET_NAME;
            } else {
                servletName = servletPath.substring(1); // skip first delimiter '/main' -> 'main'
            }
            LOG.debug("Servlet name: " + servletName);
            request.setAttribute(ShopLiterals.SERVLET_NAME, servletName);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    private void setLocalizationStorageStrategyToRequest(LocalizationStorageStrategy localizationStorageStrategy, ServletRequest servletRequest) {
        servletRequest.setAttribute(ShopLiterals.LOCALIZATION_STORAGE_STRATEGY, localizationStorageStrategy);
    }

    private Properties setAllLocalesToRequest(String localesFilePath, ServletRequest servletRequest) {
        String localesFileRealPath = servletRequest.getServletContext().getRealPath(localesFilePath);
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
        servletRequest.setAttribute(ShopLiterals.LOCALES, locales);
        return locales;
    }

    private String findFirstMatchWithBrowserLocale(Enumeration<Locale> localesFroBrowser, Properties allAvailableLocales) {
        String result = null;
        for (Iterator<Locale> it = localesFroBrowser.asIterator(); it.hasNext(); ) {
            Locale localeFroBrowser = it.next();
            String language = localeFroBrowser.getLanguage();
            if (allAvailableLocales.containsKey(language)) {
                result = language;
                LOG.debug("Set first match with browser locale: " + result);
                break;
            }
        }
        return result;
    }
}
