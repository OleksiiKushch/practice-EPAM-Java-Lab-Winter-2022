package com.epam.task11.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Oleksii Kushch
 */
public abstract class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = -9193104711863203024L;

    protected int id;

    protected Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
