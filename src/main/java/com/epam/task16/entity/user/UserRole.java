package com.epam.task16.entity.user;

import com.epam.task11.entity.Entity;

public class UserRole extends Entity {
    private String name;

    public UserRole() {
    }

    public UserRole(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
