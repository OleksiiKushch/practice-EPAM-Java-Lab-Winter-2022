package com.epam.task1.entity;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task7.create_product.ProductField;
import com.epam.task7.modified_product.BaseItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * An abstract representation of the commodity.
 * Abstract wrapper for bean classes like {@link EReader}, {@link Book}.
 *
 * @author Oleksii Kushch
 */
public class Commodity implements Cloneable, Serializable, BaseItem {
    private static final long serialVersionUID = 5773634382252297178L;

    /**
     * the identifier concrete commodity (unique value)
     */
    @ProductField(KEY = ShopLiterals.KEY_PRODUCT_ID)
    private Long id;
    /**
     * the front (external) title concrete commodity
     */
    @ProductField(KEY = ShopLiterals.KEY_PRODUCT_FRONT_TITLE)
    private String frontTitle;
    /**
     * the price concrete commodity (used BigDecimal because need the precision for money values)
     */
    @ProductField(KEY = ShopLiterals.KEY_PRODUCT_PRICE)
    private BigDecimal price;
    /**
     * the amount concrete commodity
     */
    @ProductField(KEY = ShopLiterals.KEY_PRODUCT_AMOUNT)
    private Integer amount;

    public Commodity() {
    }

    public Commodity(Long id, String frontTitle, BigDecimal price) {
        this.id = id;
        this.frontTitle = frontTitle;
        this.price = price;
    }

    public Commodity(Long id, String frontTitle, BigDecimal price, Integer amount) {
        this.id = id;
        this.frontTitle = frontTitle;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrontTitle() {
        return frontTitle;
    }

    public void setFrontTitle(String frontTitle) {
        this.frontTitle = frontTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Commodity)) return false;
        Commodity commodity = (Commodity) object;
        return id.equals(commodity.id) &&
                Objects.equals(frontTitle, commodity.frontTitle) &&
                Objects.equals(price, commodity.price) &&
                amount.equals(commodity.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, frontTitle, price, amount);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", frontTitle='" + frontTitle + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public String toStringOptional() {
        return "(id: " + id + ") " +
                getClass().getSimpleName() + ": " + frontTitle +
                ", price: " + price +
                ", amount: " + amount +
                "; ";
    }

    public String toStringWithoutPriceAndAmount() {
        return "(id: " + id + ") " + getClass().getSimpleName() + ": " + frontTitle;
    }

    public String toStringWithAmount() {
        return amount + "x - \"" + frontTitle + "\";";
    }
}
