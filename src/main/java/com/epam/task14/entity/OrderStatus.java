package com.epam.task14.entity;

import java.util.Objects;

public enum OrderStatus {
    TAKEN (1, "taken"), // принят
    CONFIRMED (2, "confirmed"), // подтвержден
    FORMED (3, "formed"), // формируется
    EXPELLED (4, "expelled"), // выслан
    COMPLETED (5, "completed"), // завершен
    CANCELED (6, "canceled"); // отменен

    private final int id;
    private final String name;

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * return null if element by id not found
     */
    public static OrderStatus getById(int id) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getId() == id)
                return status;
        }
        return null;
    }

    /**
     * return null if element by name not found
     */
    public static OrderStatus getByName(String name) {
        for (OrderStatus status : OrderStatus.values()) {
            if (Objects.equals(status.getName(), name))
                return status;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
