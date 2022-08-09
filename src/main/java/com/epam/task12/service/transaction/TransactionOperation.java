package com.epam.task12.service.transaction;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
@FunctionalInterface
public interface TransactionOperation<T> {
    T execute() throws SQLException, InvocationTargetException, IllegalAccessException;
}
