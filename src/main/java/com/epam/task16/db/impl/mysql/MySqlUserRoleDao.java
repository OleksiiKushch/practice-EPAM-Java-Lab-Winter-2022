package com.epam.task16.db.impl.mysql;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task13.db.dao.DaoException;
import com.epam.task13.db.dao.impl.mysql.MySqlEntityDao;
import com.epam.task16.db.UserRoleDao;
import com.epam.task16.entity.user.UserRole;
import com.epam.task16.mapper.impl.ResultSetToUserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserRoleDao extends MySqlEntityDao<UserRole> implements UserRoleDao {
    private static final Logger LOG = LogManager.getLogger(MySqlUserRoleDao.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlUserRoleDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public UserRole getUserRoleForId(int id) {
        return getForId(id, connectionBuilder, UserRole.class, new ResultSetToUserRole(), MySqlConstant.UserRoleQuery.GET_BY_ID);
    }

    @Override
    public UserRole getUserRoleForName(String name) {
        UserRole result = null;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.UserRoleQuery.GET_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new UserRole();
                    new ResultSetToUserRole().map(rs, result);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }
}
