package com.epam.task4.service;

import com.epam.task4.repository.factory.RepositoryFactory;
import com.epam.task4.repository.factory.MockRepoFactory;

/**
 * @author Oleksii Kushch
 */
public interface Service {
    default void initRepository() {
        if (RepositoryFactory.getRepositoryFactory().getClass() == MockRepoFactory.class) {
            initMockRepository();
        }
    }
    void initMockRepository();
}
