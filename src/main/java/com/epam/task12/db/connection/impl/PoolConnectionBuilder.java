package com.epam.task12.db.connection.impl;

import com.epam.task12.db.connection.ConnectionBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class PoolConnectionBuilder implements ConnectionBuilder {
    private static final Logger LOG = LogManager.getLogger(PoolConnectionBuilder.class);

    private static DataSource dataSource;

    private PoolConnectionBuilder() {
    }

    public static synchronized PoolConnectionBuilder getInstance() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/bookShop");
                LOG.info("Data source initial successfully.");
            } catch(NamingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return new PoolConnectionBuilder();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
