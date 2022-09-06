package com.epam.task12.service.transaction;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Oleksii Kushch
 */
@FunctionalInterface
public interface TransactionOperation<T> {
    T execute() throws InvocationTargetException, IllegalAccessException;
}
