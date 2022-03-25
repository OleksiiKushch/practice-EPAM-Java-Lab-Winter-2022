package com.epam.task4.repository.impl.mock;

import com.epam.task4.repository.OrderRepository;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class OrderRepoMockImpl implements OrderRepository {
    private OrderCatalog orderCatalog;

    public OrderRepoMockImpl(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }

    @Override
    public int insert(Order order) {
        return orderCatalog.getOrderCatalog().put(order.getDateTime(), order.getContainer()) != null ? 1 : 0;
    }

    @Override
    public List<Order> getAll() {
        return orderCatalog.getOrderCatalog().entrySet().stream()
                .map(entry -> new Order(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
