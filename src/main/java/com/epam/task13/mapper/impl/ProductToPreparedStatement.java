package com.epam.task13.mapper.impl;

import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductToPreparedStatement implements Mapper<Product, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(ProductToPreparedStatement.class);

    @Override
    public void map(Product product, PreparedStatement preparedStatement) throws MapException {
        int i = 0;
        try {
            preparedStatement.setString(++i, product.getName());
            preparedStatement.setBigDecimal(++i, product.getPrice());
            preparedStatement.setInt(++i, product.getManufacturer().getId());
            preparedStatement.setInt(++i, product.getCategory().getId());
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
