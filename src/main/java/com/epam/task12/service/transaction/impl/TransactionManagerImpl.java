package com.epam.task12.service.transaction.impl;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.service.transaction.TransactionManager;
import com.epam.task12.service.transaction.TransactionOperation;
import com.epam.task12.util.db.DBUtils;
import com.epam.task12.util.db.JdbcConnectionHolder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public class TransactionManagerImpl implements TransactionManager {
    private static final Logger LOG = LogManager.getLogger(TransactionManagerImpl.class);

    private final ConnectionBuilder connectionBuilder;

    public TransactionManagerImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public <T> T doInTransaction(TransactionOperation<T> operation) throws SQLException {
        Connection connection = connectionBuilder.getConnection();
        T result;
        try {
            connection.setAutoCommit(false);
            // connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
            JdbcConnectionHolder.setConnection(connection);

            result = operation.execute();

            connection.commit();
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            DBUtils.rollback(connection);
            throw exception;
        } catch (InvocationTargetException | IllegalAccessException exception) {
            LOG.warn(exception.getMessage());
            throw new RuntimeException(exception);
        } finally {
            JdbcConnectionHolder.unset();
            DBUtils.closeConnection(connection);
        }
        return result;
    }
}
