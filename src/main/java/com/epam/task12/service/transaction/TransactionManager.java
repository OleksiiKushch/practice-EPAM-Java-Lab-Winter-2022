package com.epam.task12.service.transaction;

/**
 * @author Oleksii Kushch
 */
public interface TransactionManager {
    <T> T doInTransaction(TransactionOperation<T> operation) throws TransactionException;
}
