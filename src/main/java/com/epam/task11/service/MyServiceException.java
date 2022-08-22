package com.epam.task11.service;

/**
 * @author Oleksii Kushch
 */
public class MyServiceException extends RuntimeException {
    public MyServiceException(String message) {
        super(message);
    }
}
