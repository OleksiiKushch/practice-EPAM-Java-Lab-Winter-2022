package com.epam.task4.repository.factory;

import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.repository.impl.mock.CartRepoMockImpl;
import com.epam.task4.repository.impl.mock.OrderRepoMockImpl;
import com.epam.task4.repository.impl.mock.ProductRepoMockImpl;

/**
 * @author Oleksii Kushch
 */
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
}
