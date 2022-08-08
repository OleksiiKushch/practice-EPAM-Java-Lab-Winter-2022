package com.epam.task12.mapper.impl;

import com.epam.task11.entity.User;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlResultSetToUser implements Mapper<ResultSet, User> {
    private static final Logger LOG = LogManager.getLogger(MySqlResultSetToUser.class);

    @Override
    public void map(ResultSet source, User target) throws MapException {
        try {
            target.setId(source.getInt(MySqlConstant.ID));
            target.setEmail(source.getString(MySqlConstant.EMAIL));
            target.setFirstName(source.getString(MySqlConstant.FIRST_NAME));
            target.setLastName(source.getString(MySqlConstant.LAST_NAME));
            target.setPassword(source.getString(MySqlConstant.PASSWORD));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException("", exception);
        }
    }
}
