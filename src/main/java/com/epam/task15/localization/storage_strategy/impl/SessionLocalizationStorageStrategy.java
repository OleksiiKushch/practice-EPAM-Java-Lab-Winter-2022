package com.epam.task15.localization.storage_strategy.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task15.localization.storage_strategy.LocalizationStorageStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionLocalizationStorageStrategy implements LocalizationStorageStrategy {
    @Override
    public String getLocale(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(ShopLiterals.LOCALE);
    }

    @Override
    public void saveLocale(String locale, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(ShopLiterals.LOCALE, locale);
    }
}
