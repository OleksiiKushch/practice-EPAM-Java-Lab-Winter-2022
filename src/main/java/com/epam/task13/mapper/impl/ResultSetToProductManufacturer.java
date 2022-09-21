package com.epam.task13.mapper.impl;

import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.product.ProductManufacturer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToProductManufacturer implements Mapper<ResultSet, ProductManufacturer> {
    private static final Logger LOG = LogManager.getLogger(ResultSetToProductManufacturer.class);

    @Override
    public void map(ResultSet resultSet, ProductManufacturer manufacturer) throws MapException {
        try {
            manufacturer.setId(resultSet.getInt(MySqlConstant.EntityModel.ID));
            manufacturer.setName(resultSet.getString(MySqlConstant.ProductManufacturerModel.NAME));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}