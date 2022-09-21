package com.epam.task13.mapper.impl;

import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.product.ProductCategory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToProductCategory implements Mapper<ResultSet, ProductCategory> {
    private static final Logger LOG = LogManager.getLogger(ResultSetToProductCategory.class);

    @Override
    public void map(ResultSet resultSet, ProductCategory category) throws MapException {
        try {
            category.setId(resultSet.getInt(MySqlConstant.EntityModel.ID));
            category.setName(resultSet.getString(MySqlConstant.ProductCategoryModel.NAME));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
