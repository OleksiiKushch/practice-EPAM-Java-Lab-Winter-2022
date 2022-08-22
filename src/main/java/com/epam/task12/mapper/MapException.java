package com.epam.task12.mapper;

import java.io.Serial;

/**
 * @author  Oleksii Kushch
 */
public class MapException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2280904009639576411L;

    public MapException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MapException(String msg) {
        super(msg);
    }
}
