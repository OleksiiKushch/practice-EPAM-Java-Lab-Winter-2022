package com.epam.task14.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task14.util.CartEntryFormData;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestToCartEntryFormData implements Mapper<HttpServletRequest, CartEntryFormData> {
    @Override
    public void map(HttpServletRequest request, CartEntryFormData cartEntryFormData) throws MapException {
        String paramProductId = request.getParameter(ShopLiterals.PRODUCT_ID);
        String paramAmount = request.getParameter(ShopLiterals.AMOUNT);
        if (StringUtils.isNotEmpty(paramProductId)) {
            cartEntryFormData.setProductId(Integer.parseInt(paramProductId));
        }
        if (StringUtils.isNotEmpty(paramAmount)) {
            cartEntryFormData.setAmount(Integer.parseInt(paramAmount));
        }
    }
}
