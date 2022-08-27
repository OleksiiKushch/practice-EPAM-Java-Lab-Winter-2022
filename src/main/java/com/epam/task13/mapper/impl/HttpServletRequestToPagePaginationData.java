package com.epam.task13.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.util.PagePaginationData;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class HttpServletRequestToPagePaginationData implements Mapper<HttpServletRequest, PagePaginationData> {
    @Override
    public void map(HttpServletRequest request, PagePaginationData pagePaginationData) throws MapException {
        pagePaginationData.setPageSize(PagePaginationData.DEFAULT_PAGE_SIZE);
        pagePaginationData.setPageNumber(PagePaginationData.DEFAULT_START_PAGE_NUMBER);
        String paramPageSize = request.getParameter(ShopLiterals.PAGE_SIZE);
        String paramPageNumber = request.getParameter(ShopLiterals.PAGE_NUMBER);
        if (Objects.nonNull(paramPageSize)) {
            pagePaginationData.setPageSize(Integer.parseInt(paramPageSize));
        }
        if (Objects.nonNull(paramPageNumber)) {
            pagePaginationData.setPageNumber(Integer.parseInt(paramPageNumber));
        }
    }
}
