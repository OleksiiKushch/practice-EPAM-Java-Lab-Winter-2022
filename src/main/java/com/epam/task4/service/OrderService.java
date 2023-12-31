package com.epam.task4.service;

import com.epam.task4.model.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Oleksii Kushch
 */
public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersFromToByDate(LocalDateTime fromDate, LocalDateTime toDate);
    Order getOrderByNearestDate(LocalDateTime nearestDate);
}
