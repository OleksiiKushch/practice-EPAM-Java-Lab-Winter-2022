package com.epam.task15.localization.storage_strategy.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task15.localization.storage_strategy.LocalizationStorageStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

public class CookieLocalizationStorageStrategy implements LocalizationStorageStrategy {
    private static final Logger LOG = LogManager.getLogger(CookieLocalizationStorageStrategy.class);
    private int lifetime;

    public CookieLocalizationStorageStrategy(int lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public String getLocale(HttpServletRequest request) {
        String localeFromCookie = Arrays.stream(request.getCookies())
                .filter(c -> ShopLiterals.LOCALE.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse(null);
        LOG.debug("Locale from cookie: " + localeFromCookie);
        String localeFromRequest = (String) request.getAttribute(ShopLiterals.LOCALE);
        LOG.debug("Locale from request: " + localeFromRequest);
        if ((Objects.nonNull(localeFromCookie) && Objects.nonNull(localeFromRequest) && !localeFromCookie.equals(localeFromRequest))
                || Objects.isNull(localeFromCookie)) {
            LOG.debug("Get from request");
            return localeFromRequest;
        }
        LOG.debug("Get from cookie");
        return localeFromCookie;
    }

    @Override
    public void saveLocale(String locale, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(ShopLiterals.LOCALE, locale);
        cookie.setMaxAge(lifetime);
        response.addCookie(cookie);
        request.setAttribute(ShopLiterals.LOCALE, locale);
    }
}
