package com.epam.task12.mapper.impl;

import com.epam.task11.entity.User;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserToPreparedStatement implements Mapper<User, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(UserToPreparedStatement.class);

    @Override
    public void map(User source, PreparedStatement target) throws MapException {
        int i = 0;
        try {
            target.setString(++i, source.getEmail());
            target.setString(++i, source.getFirstName());
            target.setString(++i, source.getLastName());
            target.setString(++i, source.getPassword());
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException("", exception);
        }
    }
}
