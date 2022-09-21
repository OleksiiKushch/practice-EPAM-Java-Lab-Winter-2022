package com.epam.task11.service;

import java.io.Serial;

/**
 * @author Oleksii Kushch
 */
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4650729471205198818L;

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
