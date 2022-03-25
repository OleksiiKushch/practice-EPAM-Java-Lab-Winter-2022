package com.epam.task4.repository.factory;

import com.epam.task4.model.data_sources.Cart;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.ProductRepository;

/**
 * @author Oleksii Kushch
 */
public abstract class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;

    public static RepositoryFactory getInstance() {
        if (repositoryFactory == null) {
            repositoryFactory = setDefaultRepositoryFactory();
        }
        return repositoryFactory;
    }

    public static void setRepositoryFactory(RepositoryFactory repositoryFactory) {
        RepositoryFactory.repositoryFactory = repositoryFactory;
    }

    public static RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    public abstract ProductRepository getProductRepository();

    public abstract OrderRepository getOrderRepository();

    public abstract CartRepository getCartRepository();

    private static RepositoryFactory setDefaultRepositoryFactory() {
        return new MockRepoFactory(new ProductCatalog(), new OrderCatalog(), new Cart());
    }
}
