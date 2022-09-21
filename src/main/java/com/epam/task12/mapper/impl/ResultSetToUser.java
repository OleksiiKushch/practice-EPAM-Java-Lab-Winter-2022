package com.epam.task12.mapper.impl;

import com.epam.task11.entity.user.User;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task16.entity.user.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleksii Kushch
 */
public class ResultSetToUser implements Mapper<ResultSet, User> {
    private static final Logger LOG = LogManager.getLogger(ResultSetToUser.class);

    @Override
    public void map(ResultSet resultSet, User user) throws MapException {
        try {
            user.setId(resultSet.getInt(MySqlConstant.EntityModel.ID));
            user.setEmail(resultSet.getString(MySqlConstant.UserModel.EMAIL));
            user.setFirstName(resultSet.getString(MySqlConstant.UserModel.FIRST_NAME));
            user.setLastName(resultSet.getString(MySqlConstant.UserModel.LAST_NAME));
            user.setPassword(resultSet.getString(MySqlConstant.UserModel.PASSWORD));
            user.setRole(new UserRole(resultSet.getInt(MySqlConstant.UserModel.ROLE_ID)));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
