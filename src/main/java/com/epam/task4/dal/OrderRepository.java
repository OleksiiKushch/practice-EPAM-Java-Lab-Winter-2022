package com.epam.task4.dal;

import com.epam.task4.model.entity.Order;

public interface OrderRepository extends EntityRepository<Order> {
    int insert(Order order);
}
