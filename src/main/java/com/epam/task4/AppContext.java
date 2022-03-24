package com.epam.task4;

import com.epam.task4.repository.factory.MockRepoFactory;
import com.epam.task4.repository.factory.RepositoryFactory;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    public void init() {
        initDataSource();
    }

    private void initDataSource() {
        RepositoryFactory.setRepositoryFactory(new MockRepoFactory());

    }
}
