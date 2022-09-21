package com.epam.task13.mapper.impl;

import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.product.Product;
import com.epam.task13.entity.product.ProductCategory;
import com.epam.task13.entity.product.ProductManufacturer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToProduct implements Mapper<ResultSet, Product> {
    private static final Logger LOG = LogManager.getLogger(ResultSetToProduct.class);

    @Override
    public void map(ResultSet resultSet, Product product) throws MapException {
        try {
            product.setId(resultSet.getInt(MySqlConstant.EntityModel.ID));
            product.setName(resultSet.getString(MySqlConstant.ProductModel.NAME));
            product.setPrice(resultSet.getBigDecimal(MySqlConstant.ProductModel.PRICE));
            product.setManufacturer(new ProductManufacturer(resultSet.getInt(MySqlConstant.ProductModel.MANUFACTURER_ID)));
            product.setCategory(new ProductCategory(resultSet.getInt(MySqlConstant.ProductModel.CATEGORY_ID)));
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
