package com.epam.task12.db.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class DBUtils {
    private static final Logger LOG = LogManager.getLogger(DBUtils.class);

    public static void rollback(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                LOG.warn(exception.getMessage());
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException exception) {
                LOG.warn(exception.getMessage());
            }
        }
    }
}
