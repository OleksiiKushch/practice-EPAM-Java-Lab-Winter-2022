package com.epam.task4.model.entity;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ShopLiterals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class Order {
    private LocalDateTime dateTime;
    private List<Commodity> container;

    public Order() {
    }

    public Order(LocalDateTime dateTime, List<Commodity> container) {
        this.dateTime = dateTime;
        this.container = container;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Commodity> getContainer() {
        return container;
    }

    public void setContainer(List<Commodity> container) {
        this.container = container;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateTime=" + dateTime +
                ", container=" + container +
                '}';
    }

    public String toStringWithoutId() {
        return "(Date: " + dateTime.format(DateTimeFormatter.ofPattern(ShopLiterals.DATE_FORMAT)) + ") { " +
                container.stream()
                        .map(product -> product.getAmount() + "x - \"" + product.getFrontTitle() + "\" ")
                        .collect(Collectors.joining()) + "};";
    }
}
