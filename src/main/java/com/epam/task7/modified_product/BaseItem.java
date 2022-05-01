package com.epam.task7.modified_product;

import java.math.BigDecimal;

/**
 * @author Oleksii Kushch
 */
public interface BaseItem {
    @Getter(FIELD_NAME = "id")
    Long getId();
    @Setter(FIELD_NAME = "id")
    void setId(Long id);
    @Getter(FIELD_NAME = "frontTitle")
    String getFrontTitle();
    @Setter(FIELD_NAME = "frontTitle")
    void setFrontTitle(String frontTitle);
    @Getter(FIELD_NAME = "price")
    BigDecimal getPrice();
    @Setter(FIELD_NAME = "price")
    void setPrice(BigDecimal price);
    @Getter(FIELD_NAME = "amount")
    Integer getAmount();
    @Setter(FIELD_NAME = "amount")
    void setAmount(Integer amount);
    String toString();
}
