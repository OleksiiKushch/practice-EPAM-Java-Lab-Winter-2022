package com.epam.task4;

import com.epam.task4.controller.CommandContainer;
import com.epam.task4.controller.InitCommandContainer;
import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.Cart;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.repository.factory.MockRepoFactory;
import com.epam.task4.repository.factory.RepositoryFactory;

import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    private final Scanner scanner;
    private final CommandContainer commandContainer;

    public AppContext() {
        initDataSource();
        scanner = new Scanner(System.in);
        commandContainer = InitCommandContainer.init(new CommandContainer());
    }

    public Scanner getScanner() {
        return scanner;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    private void initDataSource() {
        RepositoryFactory.setRepositoryFactory(new MockRepoFactory(
                InitMockResources.initProductCatalog(new ProductCatalog()),
                InitMockResources.initOrderCatalog(new OrderCatalog()),
                new Cart()));
    }
}
