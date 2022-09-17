package com.epam.task14.db.dao.impl;

import com.epam.task12.db.connection.ConnectionBuilder;
import com.epam.task12.db.dao.impl.mysql.MySqlConstant;
import com.epam.task13.db.dao.DaoException;
import com.epam.task13.db.dao.impl.mysql.MySqlEntityDao;
import com.epam.task13.entity.product.Product;
import com.epam.task14.db.dao.OrderDao;
import com.epam.task14.entity.Order;
import com.epam.task14.mapper.impl.CartEntryToPreparedStatement;
import com.epam.task14.mapper.impl.OrderToPreparedStatement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class OrderDaoImpl extends MySqlEntityDao<Order> implements OrderDao {
    private static final Logger LOG = LogManager.getLogger(OrderDaoImpl.class);

    private final ConnectionBuilder connectionBuilder;

    public OrderDaoImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public int insert(Order order) throws DaoException {
        return insert(order, connectionBuilder, new OrderToPreparedStatement(), MySqlConstant.OrderQuery.INSERT);
    }

    @Override
    public void insertHasProducts(Order order) throws DaoException {
        try (PreparedStatement ps = connectionBuilder.getConnection().prepareStatement(MySqlConstant.OrderProductRelationQuery.INSERT_HAS_PRODUCT)) {
            for (Map.Entry<Product, Integer> cartEntry : order.getCart().getContent()) {
                ps.setInt(1, order.getId());
                new CartEntryToPreparedStatement().map(cartEntry, ps);
                ps.executeUpdate();
            }
        } catch (SQLException exception) {
            LOG.warn(exception.getMessage(), exception);
            throw new DaoException(exception.getMessage(), exception);
        }
    }
}
