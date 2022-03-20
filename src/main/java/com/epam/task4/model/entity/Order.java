package com.epam.task4.model.entity;

import com.epam.task1.entity.Commodity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Order extends Entity {
    private LocalDateTime dateTime;
    private List<Commodity> container;

    public Order(Long id, LocalDateTime dateTime, List<Commodity> container) {
        super(id);
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
        return "(Date: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") { " +
                container.stream()
                        .map(product -> product.getAmount() + "x - \"" + product.getFrontTitle() + "\" ")
                        .collect(Collectors.joining()) + "}";
    }
}
