package com.epam.task1.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * An abstract representation of the commodity.
 * Abstract wrapper for bean classes like {@link EReader}, {@link Book}.
 *
 * @author Oleksii Kushch
 */
public abstract class Commodity implements Cloneable, Serializable {
    private static final long serialVersionUID = 5773634382252297178L;

    /**
     * the identifier concrete commodity (unique value)
     */
    private Long id;
    /**
     * the front (external) title concrete commodity
     */
    private String frontTitle;
    /**
     * the price concrete commodity (used BigDecimal because need the precision for money values)
     */
    private BigDecimal price;
    /**
     * the amount concrete commodity
     */
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
        return Objects.equals(id, commodity.id) &&
                Objects.equals(frontTitle, commodity.frontTitle) &&
                Objects.equals(price, commodity.price) &&
                Objects.equals(amount, commodity.amount);
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
