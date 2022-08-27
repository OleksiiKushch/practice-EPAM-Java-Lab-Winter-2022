package com.epam.task13.db.dao;

import java.io.Serial;

public class DaoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5485260742062736505L;

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoException(String msg) {
        super(msg);
    }
}
