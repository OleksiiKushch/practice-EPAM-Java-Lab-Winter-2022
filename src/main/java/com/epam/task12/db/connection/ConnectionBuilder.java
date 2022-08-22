package com.epam.task12.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}