package com.epam.task13.entity.product;

import com.epam.task11.entity.Entity;

public class ProductManufacturer extends Entity {
    private String name;
    private int count;

    public ProductManufacturer() {
    }

    public ProductManufacturer(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductManufacturer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
