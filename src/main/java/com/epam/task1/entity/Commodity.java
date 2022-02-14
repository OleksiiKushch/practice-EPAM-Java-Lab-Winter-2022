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
public abstract class Commodity implements Serializable {
    private static final long serialVersionUID = 5773634382252297178L;

    /**
     * the identifier concrete commodity (unique value)
     */
    private Long id;
    /**
     * the price concrete commodity (used BigDecimal because need the precision for money values)
     */
    private BigDecimal price;

    public Commodity() {}

    public Commodity(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commodity)) return false;
        Commodity commodity = (Commodity) o;
        return Objects.equals(id, commodity.id) &&
                Objects.equals(price, commodity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
