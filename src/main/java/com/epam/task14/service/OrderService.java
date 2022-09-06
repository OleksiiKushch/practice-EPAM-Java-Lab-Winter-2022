package com.epam.task14.service;

import com.epam.task12.service.transaction.Transaction;
import com.epam.task14.entity.Order;

public interface OrderService {
    @Transaction
    Order checkout(Order order);
}
