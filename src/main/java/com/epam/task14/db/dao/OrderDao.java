package com.epam.task14.db.dao;

import com.epam.task13.db.dao.DaoException;
import com.epam.task14.entity.Order;

public interface OrderDao {
    int insert(Order order) throws DaoException;
    void insertHasProducts(Order order) throws DaoException;
}
