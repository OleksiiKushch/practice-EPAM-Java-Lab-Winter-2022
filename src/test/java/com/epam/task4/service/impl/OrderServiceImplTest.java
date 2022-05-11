package com.epam.task4.service.impl;

import com.epam.task4.model.entity.Order;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.impl.mock.OrderRepositoryImpl;
import com.epam.task4.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;

class OrderServiceImplTest {

    @Test
    void getOrderByNearestDate_ifOrderCatalogIsEmpty() {
        OrderRepository orderRepository = Mockito.mock(OrderRepositoryImpl.class);

        OrderService orderService = new OrderServiceImpl(orderRepository);

        Mockito.when(orderRepository.getAll()).thenReturn(new ArrayList<>());

        Order result = orderService.getOrderByNearestDate(LocalDateTime.now());

        assertNull(result);
    }
}