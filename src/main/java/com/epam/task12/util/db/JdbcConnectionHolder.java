package com.epam.task12.util.db;

import java.sql.Connection;

/**
 * @author Oleksii Kushch
 */
public class JdbcConnectionHolder {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        connectionThreadLocal.set(connection);
    }

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void unset() {
        connectionThreadLocal.remove();
    }
}
