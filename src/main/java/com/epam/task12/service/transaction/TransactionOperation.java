package com.epam.task12.service.transaction;

import com.epam.task13.db.dao.DaoException;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Oleksii Kushch
 */
@FunctionalInterface
public interface TransactionOperation<T> {
    T execute() throws DaoException, InvocationTargetException, IllegalAccessException;
}
