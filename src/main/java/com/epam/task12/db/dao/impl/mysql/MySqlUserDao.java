package com.epam.task12.db.dao.impl.mysql;

import com.epam.task11.entity.User;
import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.UserDao;
import com.epam.task12.mapper.impl.MySqlResultSetToUser;
import com.epam.task12.mapper.impl.UserToPreparedStatement;
import com.epam.task13.db.dao.DaoException;
import com.epam.task13.db.dao.impl.mysql.MySqlEntityDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public class MySqlUserDao extends MySqlEntityDao<User> implements UserDao {
    private static final Logger LOG = LogManager.getLogger(MySqlUserDao.class);

    private final ConnectionBuilder connectionBuilder;

    public MySqlUserDao(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public int insert(User user) throws DaoException {
        return insert(user, connectionBuilder, new UserToPreparedStatement(), MySqlConstant.UserQuery.INSERT);
    }

    @Override
    public User getUserForEmail(String email) throws DaoException {
        User result = null;
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(MySqlConstant.UserQuery.GET_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User();
                    new MySqlResultSetToUser().map(rs, result);
                }
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new DaoException(exception.getMessage(), exception);
        }
        return result;
    }
}
