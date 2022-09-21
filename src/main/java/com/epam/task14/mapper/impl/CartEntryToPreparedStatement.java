package com.epam.task14.mapper.impl;

import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task13.entity.product.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class CartEntryToPreparedStatement implements Mapper<Map.Entry<Product, Integer>, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(CartEntryToPreparedStatement.class);

    @Override
    public void map(Map.Entry<Product, Integer> cartEntry, PreparedStatement preparedStatement) throws MapException {
        int i = 1; // because first is order id
        try {
            preparedStatement.setInt(++i, cartEntry.getKey().getId());
            preparedStatement.setBigDecimal(++i, cartEntry.getKey().getPrice());
            preparedStatement.setInt(++i, cartEntry.getValue());
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
