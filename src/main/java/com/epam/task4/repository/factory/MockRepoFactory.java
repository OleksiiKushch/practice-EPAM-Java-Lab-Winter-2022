package com.epam.task4.repository.factory;

import com.epam.task4.model.data_sources.Cart;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.data_sources.ProductCatalog;
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
    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    public MockRepoFactory(ProductCatalog productCatalog, OrderCatalog orderCatalog, Cart cart) {
        this.productCatalog = productCatalog;
        this.orderCatalog = orderCatalog;
        this.cart = cart;
    }

    @Override
    public ProductRepository getProductRepository() {
        return new ProductRepoMockImpl(productCatalog);
    }

    @Override
    public OrderRepository getOrderRepository() {
        return new OrderRepoMockImpl(orderCatalog);
    }

    @Override
    public CartRepository getCartRepository() {
        return new CartRepoMockImpl(cart);
    }
}
