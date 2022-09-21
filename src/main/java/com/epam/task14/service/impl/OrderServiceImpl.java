package com.epam.task14.service.impl;

import com.epam.task11.service.ServiceException;
import com.epam.task11.service.ServiceMessages;
import com.epam.task12.service.transaction.Transaction;
import com.epam.task12.service.transaction.TransactionException;
import com.epam.task12.service.transaction.TransactionManager;
import com.epam.task14.db.dao.OrderDao;
import com.epam.task14.entity.Order;
import com.epam.task14.entity.OrderStatus;
import com.epam.task14.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Proxy;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class);

    public static final String NEW_ORDER_DEFAULT_STATE_DETAIL = "New order!";

    private OrderDao orderDao;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderServiceImpl(OrderDao orderDao, TransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
    }

    public static OrderService getInstance(OrderDao orderDao, TransactionManager transactionManager) {
        OrderServiceImpl orderService = new OrderServiceImpl(orderDao, transactionManager);
        return (OrderService) Proxy.newProxyInstance(
                OrderServiceImpl.class.getClassLoader(),
                new Class[] { OrderService.class },
                (proxy, method, methodArgs) -> {
                    if (method.isAnnotationPresent(Transaction.class)) {
                        try {
                            LOG.debug("Method: " + method.getName() + " invoke transactionally");
                            return orderService.transactionManager.doInTransaction(() -> method.invoke(orderService, methodArgs));
                        } catch (TransactionException exception) {
                            throw new ServiceException(exception.getMessage());
                        }
                    } else {
                        LOG.debug("Method: " + method.getName() + " invoke NON transactionally");
                        return method.invoke(orderService, methodArgs);
                    }
                });
    }

    @Override
    public Order checkout(Order order) {
        if (order.getCart().isEmpty()) {
            throw new ServiceException(ServiceMessages.CART_IS_EMPTY);
        }
        order.setOrderStatus(OrderStatus.FORMED);
        order.setStateDetail(NEW_ORDER_DEFAULT_STATE_DETAIL);
        orderDao.insert(order);
        LOG.debug("Order after insert: " + order);
        orderDao.insertHasProducts(order);
        return order;
    }
}
