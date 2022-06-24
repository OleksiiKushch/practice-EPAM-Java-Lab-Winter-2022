package com.epam.task11.entity;

import java.io.Serial;
import java.io.Serializable;

public abstract class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = -9193104711863203024L;

    protected int id;

    protected Entity() {
        // nothing to do
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
