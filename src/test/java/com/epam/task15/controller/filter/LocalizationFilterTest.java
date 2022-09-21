package com.epam.task15.controller.filter;

import com.epam.task11.constant.ShopLiterals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

class LocalizationFilterTest {
    ServletContext context;
    HttpSession session;
    ServletRequest request;
    ServletResponse response;
    FilterChain filterChain;
    FilterConfig filterConfig;

    @BeforeEach
    void setUp() {
        context = Mockito.mock(ServletContext.class);
        session = Mockito.mock(HttpSession.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        filterConfig = Mockito.mock(FilterConfig.class);

        Mockito.when(filterConfig.getInitParameter("locales")).thenReturn("");
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getRealPath("")).thenReturn("src/test/resources/test-locales.properties");
        Mockito.when(((HttpServletRequest) request).getSession()).thenReturn(session);
    }

    @Test
    void testDoFilter_ifSessionLocStorageStrat_andRequestToChangeLocale() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("session");
        Mockito.when(request.getParameter(ShopLiterals.LOCALE)).thenReturn("en");

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    @Test
    void testDoFilter_ifCookieLocStorageStrat_andRequestToChangeLocale() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("cookie");
        Mockito.when(request.getServletContext().getInitParameter("localization-cookie-lifetime")).thenReturn("20");
        Mockito.when(request.getParameter(ShopLiterals.LOCALE)).thenReturn("en");

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    @Test
    void testDoFilter_ifSessionLocStorageStrat_andUpdateCurrentLocale() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("session");
        Mockito.when(session.getAttribute(ShopLiterals.LOCALE)).thenReturn("en");

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    @Test
    void testDoFilter_ifCookieLocStorageStrat_andUpdateCurrentLocale() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("cookie");
        Mockito.when(request.getServletContext().getInitParameter("localization-cookie-lifetime")).thenReturn("20");
        Mockito.when(((HttpServletRequest) request).getCookies()).thenReturn(new Cookie[] {new Cookie(ShopLiterals.LOCALE, "en")});

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    @Test
    void testDoFilter_ifLocaleNotPresentInStorage() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("session");
        Enumeration<Locale> localesFromBrowser = getDefaultMockLocalesForBrowser();
        Mockito.when(request.getLocales()).thenReturn(localesFromBrowser);

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    @Test
    void testDoFilter_ifLocaleNotPresentInStorage_andNotMatchesLocalesFromBrowserAndAvailableLocales() throws ServletException, IOException {
        Mockito.when(filterConfig.getInitParameter("localization-storage-strategy")).thenReturn("session");
        Enumeration<Locale> localesFromBrowser = getNotMatchesMockLocalesForBrowser();
        Mockito.when(request.getLocales()).thenReturn(localesFromBrowser);

        LocalizationFilter testFilter = new LocalizationFilter();

        testFilter.init(filterConfig);
        testFilter.doFilter(request, response, filterChain);
        testFilter.destroy();
    }

    private Enumeration<Locale> getDefaultMockLocalesForBrowser() {
        Set<Locale> localeSet = new HashSet<>();
        localeSet.add(new Locale("ru"));
        localeSet.add(new Locale("en"));
        return new Vector<>(localeSet).elements();
    }

    private Enumeration<Locale> getNotMatchesMockLocalesForBrowser() {
        Set<Locale> localeSet = new HashSet<>();
        localeSet.add(new Locale("ua"));
        return new Vector<>(localeSet).elements();
    }
}