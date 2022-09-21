package com.epam.task13.mapper.impl;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.product.ProductCategory;
import com.epam.task13.entity.product.ProductManufacturer;
import com.epam.task13.util.ProductFilterFormBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class HttpServletRequestToProductFilterFormBean implements Mapper<HttpServletRequest, ProductFilterFormBean> {
    private static final Logger LOG = LogManager.getLogger(HttpServletRequestToProductFilterFormBean.class);

    private List<ProductManufacturer> manufacturers;
    private List<ProductCategory> categories;

    public HttpServletRequestToProductFilterFormBean(List<ProductManufacturer> manufacturers, List<ProductCategory> categories) {
        this.manufacturers = manufacturers;
        this.categories = categories;
    }

    @Override
    public void map(HttpServletRequest request, ProductFilterFormBean productFilterFormBean) throws MapException {
        productFilterFormBean.setNamePattern(request.getParameter(ShopLiterals.FILTER_PRODUCT_NAME_PATTERN));
        String minPriceParam = request.getParameter(ShopLiterals.FILTER_MIN_PRICE);
        String maxPriceParam = request.getParameter(ShopLiterals.FILTER_MAX_PRICE);
        if (StringUtils.isNotEmpty(minPriceParam)) { // need do it in validation class (isEmpty)
            productFilterFormBean.setMinPrice(new BigDecimal(minPriceParam));
        }
        if (StringUtils.isNotEmpty(maxPriceParam)) { // need do it in validation class (isEmpty)
            productFilterFormBean.setMaxPrice(new BigDecimal(maxPriceParam));
        }
        for (ProductManufacturer manufacturer : manufacturers) {
            String filteringManufacturerParameter = request.getParameter(ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX + manufacturer.getId());
            if (Objects.nonNull(filteringManufacturerParameter)) {
                productFilterFormBean.getManufactureIds().add(manufacturer.getId());
            }
        }
        for (ProductCategory category : categories) {
            String filteringCategoryParameter = request.getParameter(ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX + category.getId());
            if (Objects.nonNull(filteringCategoryParameter)) {
                productFilterFormBean.getCategoryIds().add(category.getId());
            }
        }
    }
}
