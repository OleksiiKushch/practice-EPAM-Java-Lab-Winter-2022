package com.epam.task12.service.transaction.impl;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.service.transaction.TransactionException;
import com.epam.task12.service.transaction.TransactionManager;
import com.epam.task12.service.transaction.TransactionOperation;
import com.epam.task12.util.db.DBUtils;
import com.epam.task12.util.db.JdbcConnectionHolder;
import com.epam.task13.db.dao.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public class MySqlTransactionManager implements TransactionManager {
    private static final Logger LOG = LogManager.getLogger(MySqlTransactionManager.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlTransactionManager(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public <T> T doInTransaction(TransactionOperation<T> operation) throws TransactionException {
        T result;
        Connection connection = null;
        try {
            connection = connectionBuilder.getConnection();
            connection.setAutoCommit(false);
            // connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
            JdbcConnectionHolder.setConnection(connection);

            result = operation.execute();

            connection.commit();
        } catch (SQLException | DaoException exception) {
            LOG.warn(exception.getMessage());
            DBUtils.rollback(connection);
            throw new TransactionException(exception.getMessage(), exception);
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
