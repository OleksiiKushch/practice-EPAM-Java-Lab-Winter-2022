package com.epam.task4.dal.impl.mock;

import com.epam.task4.dal.CartHistoryRepository;
import com.epam.task4.dal.CartRepository;
import com.epam.task4.dal.OrderRepository;
import com.epam.task4.dal.ProductRepository;
import com.epam.task4.dal.RepositoryFactory;

public class MockRepoFactory extends RepositoryFactory {
    @Override
    public OrderRepository getOrderRepository() {
        return new OrderRepoMockImpl();
    }

    @Override
    public ProductRepository getProductRepository() {
        return new ProductRepoMockImpl();
    }

    @Override
    public CartRepository getCartRepository() {
        return new CartRepoMockImpl();
    }

    @Override
    public CartHistoryRepository getCartHistoryRepository() {
        return new CartHistoryRepoMockImpl();
    }
}
