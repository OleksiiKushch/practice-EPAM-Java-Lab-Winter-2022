package com.epam.task16.mapper.impl;

import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task16.entity.user.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToUserRole implements Mapper<ResultSet, UserRole> {
    private static final Logger LOG = LogManager.getLogger(ResultSetToUserRole.class);

    @Override
    public void map(ResultSet resultSet, UserRole userRole) throws MapException {
        try {
            userRole.setId(resultSet.getInt(MySqlConstant.EntityModel.ID));
            userRole.setName(resultSet.getString(MySqlConstant.UserRoleModel.NAME));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
