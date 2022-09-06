package com.epam.task14.mapper.impl;

import com.epam.task12.mapper.MapException;
import com.epam.task12.mapper.Mapper;
import com.epam.task14.entity.Order;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderToPreparedStatement implements Mapper<Order, PreparedStatement> {
    private static final Logger LOG = LogManager.getLogger(OrderToPreparedStatement.class);

    @Override
    public void map(Order order, PreparedStatement preparedStatement) throws MapException {
        int i = 0;
        try {
            preparedStatement.setInt(++i, order.getOrderStatus().getId());
            preparedStatement.setString(++i, order.getStateDetail());
            preparedStatement.setString(++i, order.getDelivery());
            preparedStatement.setInt(++i, order.getUser().getId());
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage());
            throw new MapException(exception.getMessage(), exception);
        }
    }
}
