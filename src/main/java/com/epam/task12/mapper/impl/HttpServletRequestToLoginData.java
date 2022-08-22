package com.epam.task12.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task12.util.LoginData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Oleksii Kushch
 */
public class HttpServletRequestToLoginData implements Mapper<HttpServletRequest, LoginData> {
    @Override
    public void map(HttpServletRequest request, LoginData loginData) throws MapException {
        loginData.setEmail(request.getParameter(ShopLiterals.EMAIL));
        loginData.setPassword(request.getParameter(ShopLiterals.PASSWORD));
    }
}
