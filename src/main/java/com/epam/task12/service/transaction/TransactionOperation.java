package com.epam.task12.service.transaction;

import java.sql.SQLException;

public interface TransactionOperation<T> {
    T execute() throws SQLException;
}
