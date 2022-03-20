package com.epam.task4.service;

import com.epam.task4.dal.RepositoryFactory;
import com.epam.task4.dal.impl.mock.MockRepoFactory;

public interface EntityService {
    default void initRepository() {
        if (RepositoryFactory.getRepositoryFactoryClass().equals(MockRepoFactory.class)) {
            initMockRepository();
        }
    }
    void initMockRepository();
}
