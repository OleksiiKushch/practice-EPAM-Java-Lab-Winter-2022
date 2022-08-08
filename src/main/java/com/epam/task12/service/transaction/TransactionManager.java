package com.epam.task12.service.transaction;

import java.sql.SQLException;

public interface TransactionManager {
    <T> T doInTransaction(TransactionOperation<T> operation) throws SQLException;
}
