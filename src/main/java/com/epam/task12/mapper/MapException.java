package com.epam.task12.mapper;

import java.io.Serial;

/**
 * @author  Oleksii Kushch
 */
public class MapException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1493800882909173795L;

    public MapException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MapException(String msg) {
        super(msg);
    }
}
