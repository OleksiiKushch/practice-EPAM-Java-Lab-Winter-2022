package com.epam.task13.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductFilterFormBean {
    private String namePattern;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<Integer> manufactureIds;
    private List<Integer> categoryIds;

    public ProductFilterFormBean() {
        manufactureIds = new ArrayList<>();
        categoryIds = new ArrayList<>();
    }

    public String getNamePattern() {
        return namePattern;
    }

    public void setNamePattern(String namePattern) {
        this.namePattern = namePattern;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getManufactureIds() {
        return manufactureIds;
    }

    public void setManufactureIds(List<Integer> manufactureIds) {
        this.manufactureIds = manufactureIds;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "ProductFilterFormBean{" +
                "namePattern='" + namePattern + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", manufactureIds=" + manufactureIds +
                ", categoryIds=" + categoryIds +
                '}';
    }

    public int getNumberOfParametersSet() {
        int result = 0;
        if (Objects.nonNull(namePattern)) {
            result++;
        }
        if (Objects.nonNull(minPrice)) {
            result++;
        }
        if (Objects.nonNull(maxPrice)) {
            result++;
        }
        if (Objects.nonNull(manufactureIds)) {
            result += manufactureIds.size();
        }
        if (Objects.nonNull(categoryIds)) {
            result += categoryIds.size();
        }
        return result;
    }
}
