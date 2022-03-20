package com.epam.task4.service;

import com.epam.task4.model.entity.Order;

import java.util.List;

public interface OrderService extends EntityService {
    List<Order> getAllOrders();
    List<Order> getOrdersFromToByDate();
    Order getOrderByNearestDate();
}
