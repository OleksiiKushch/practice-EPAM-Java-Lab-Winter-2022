package com.epam.task12.mapper.impl;

import com.epam.task11.entity.user.User;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public class UserToPreparedStatement implements Mapper<User, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(UserToPreparedStatement.class);

    @Override
    public void map(User user, PreparedStatement preparedStatement) throws MapException {
        int i = 0;
        try {
            preparedStatement.setString(++i, user.getEmail());
            preparedStatement.setString(++i, user.getFirstName());
            preparedStatement.setString(++i, user.getLastName());
            preparedStatement.setString(++i, user.getPassword());
            preparedStatement.setInt(++i, user.getRole().getId());
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
