package com.epam.task12.util.db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DBUtils {
    private static final Logger LOG = LogManager.getLogger(DBUtils.class);

    public static void rollback(Connection connection) throws SQLException {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                LOG.warn(exception.getMessage());
                throw exception;
            }
        }
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException exception) {
                LOG.warn(exception.getMessage());
                throw exception;
            }
        }
    }
}
