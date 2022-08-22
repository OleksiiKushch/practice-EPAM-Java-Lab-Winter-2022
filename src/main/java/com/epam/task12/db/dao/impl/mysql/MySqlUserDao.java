package com.epam.task12.db.dao.impl.mysql;

import com.epam.task11.entity.User;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.UserDao;
import com.epam.task12.mapper.impl.MySqlResultSetToUser;
import com.epam.task12.mapper.impl.UserToPreparedStatement;
import com.epam.task12.service.transaction.impl.TransactionManagerImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author Oleksii Kushch
 */
public class MySqlUserDao implements UserDao {
    private static final Logger LOG = LogManager.getLogger(TransactionManagerImpl.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlUserDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public User getUserForEmail(String email) throws SQLException {
        User result = null;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.UserQuery.GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User();
                    new MySqlResultSetToUser().map(rs, result);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw exception;
        }
        return result;
    }

    @Override
    public int create(User user) throws SQLException {
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.UserQuery.CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            new UserToPreparedStatement().map(user, ps);
            int result = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
            return result;
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw exception;
        }
    }
}
