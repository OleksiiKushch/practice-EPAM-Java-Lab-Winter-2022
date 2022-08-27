package com.epam.task12.service.transaction;

import java.io.Serial;

public class TransactionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5702513324433078747L;

    public TransactionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TransactionException(String msg) {
        super(msg);
    }
}
