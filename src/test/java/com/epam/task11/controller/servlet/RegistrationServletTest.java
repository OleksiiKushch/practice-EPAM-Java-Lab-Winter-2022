package com.epam.task11.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

class RegistrationServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext context;
    private CaptchaDataStorageStrategy captchaDataStorageStrategy;

    @BeforeEach
    void setUp() {
        request = Mockito.mock(HttpServletRequest.class);

        response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getContextPath()).thenReturn("");

        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        context = Mockito.mock(ServletContext.class);
        Mockito.when(request.getServletContext()).thenReturn(context);

        captchaDataStorageStrategy = Mockito.mock(CaptchaDataStorageStrategy.class);
        Mockito.when(context.getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY)).thenReturn(captchaDataStorageStrategy);

        Mockito.when(request.getParameter(ShopLiterals.EMAIL)).thenReturn("unicronix2002@gmail.com");

        Mockito.when(request.getParameter(ShopLiterals.FIRST_NAME)).thenReturn("Alex");
        Mockito.when(request.getParameter(ShopLiterals.LAST_NAME)).thenReturn("Fisher");
        Mockito.when(request.getParameter(ShopLiterals.PASSWORD)).thenReturn("123456");
        Mockito.when(request.getParameter(ShopLiterals.CONFIRMATION_PASSWORD)).thenReturn("123456");
        Mockito.when(request.getParameter(ShopLiterals.CAPTCHA)).thenReturn("9999");
        Mockito.when(captchaDataStorageStrategy.getCaptchaLifetime(request)).thenReturn(10);
        Mockito.when(captchaDataStorageStrategy.getStoredCode(request)).thenReturn("9999");
        Mockito.when(captchaDataStorageStrategy.getCaptchaTimeout()).thenReturn(20);
    }

    @Test
    void testDoPost_IfAllDataIsValid() throws IOException {
        new RegistrationServlet().doPost(request, response);
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/main");
    }

    @Test
    void testDoPost_IfAccountAlreadyExists() throws IOException {
        Mockito.when(request.getParameter(ShopLiterals.EMAIL)).thenReturn("UnicroniX2001@gmail.com");
        new RegistrationServlet().doPost(request, response);
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/registration");
    }

    @Test
    void testDoPost_IfPasswordsNotMatch() throws IOException {
        Mockito.when(request.getParameter(ShopLiterals.CONFIRMATION_PASSWORD)).thenReturn("123455");
        new RegistrationServlet().doPost(request, response);
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/registration");
    }

    @Test
    void testDoPost_IfCaptchaNotMatch() throws IOException {
        Mockito.when(request.getParameter(ShopLiterals.CAPTCHA)).thenReturn("0000");
        new RegistrationServlet().doPost(request, response);
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/registration");
    }

    @Test
    void testDoPost_IfCaptchaTimeout() throws IOException {
        Mockito.when(captchaDataStorageStrategy.getCaptchaLifetime(request)).thenReturn(30);
        new RegistrationServlet().doPost(request, response);
        Mockito.verify(response, Mockito.times(1)).sendRedirect("/registration");
    }
}