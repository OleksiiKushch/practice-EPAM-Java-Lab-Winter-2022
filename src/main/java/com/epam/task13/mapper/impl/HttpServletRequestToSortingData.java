package com.epam.task13.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.util.SortingData;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestToSortingData implements Mapper<HttpServletRequest, SortingData> {
    @Override
    public void map(HttpServletRequest request, SortingData sortingData) throws MapException {
        sortingData.setParameter(request.getParameter(ShopLiterals.SORT_PARAMETER));
        sortingData.setOrdering(request.getParameter(ShopLiterals.SORT_ORDERING_PARAMETER));
    }
}
