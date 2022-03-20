package com.epam.task4.dal;

import java.lang.reflect.InvocationTargetException;

public abstract class RepositoryFactory {
    private static RepositoryFactory instance;

    private static Class<RepositoryFactory> repositoryFactoryClass;

    public static RepositoryFactory getInstance() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        if (instance == null) {
            instance = repositoryFactoryClass.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public static void setRepositoryFactory(Class<? extends RepositoryFactory> repositoryFactoryClass) {
        instance = null;
        RepositoryFactory.repositoryFactoryClass = (Class<RepositoryFactory>) repositoryFactoryClass;
    }

    public static Class<RepositoryFactory> getRepositoryFactoryClass() {
        return repositoryFactoryClass;
    }

    public abstract OrderRepository getOrderRepository();

    public abstract ProductRepository getProductRepository();

    public abstract CartRepository getCartRepository();

    public abstract CartHistoryRepository getCartHistoryRepository();
}
