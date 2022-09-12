package com.epam.task15.localization.storage_strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocalizationStorageStrategy {
    String getLocale(HttpServletRequest request);
    void saveLocale(String locale, HttpServletRequest request, HttpServletResponse response);
}
