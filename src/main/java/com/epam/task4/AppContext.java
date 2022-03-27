package com.epam.task4;

import com.epam.task4.controller.CommandContainer;
import com.epam.task4.controller.command.CheckoutCmd;
import com.epam.task4.controller.command.HelpCmd;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.controller.command.StopCmd;
import com.epam.task4.controller.command.ViewCartCmd;
import com.epam.task4.controller.command.ViewLatestProductsFromCartCmd;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrderCatalogCmd;
import com.epam.task4.controller.command.ViewOrdersFromToByDateCmd;
import com.epam.task4.controller.command.ViewProductCatalogCmd;
import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.Cart;
import com.epam.task4.model.data_sources.OrderCatalog;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.OrderRepository;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.repository.impl.mock.CartRepositoryImpl;
import com.epam.task4.repository.impl.mock.OrderRepositoryImpl;
import com.epam.task4.repository.impl.mock.ProductRepositoryImpl;
import com.epam.task4.service.CartService;
import com.epam.task4.service.OrderService;
import com.epam.task4.service.ProductService;
import com.epam.task4.service.impl.CartServiceImpl;
import com.epam.task4.service.impl.OrderServiceImpl;
import com.epam.task4.service.impl.ProductServiceImpl;

import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    private ProductService productService;
    private OrderService orderService;
    private CartService cartService;

    private CommandContainer commandContainer;

    private final Scanner scanner;

    public AppContext() {
        initDataSource();
        initRepository();
        initService();
        initController();
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    private void initDataSource() {
        productCatalog = InitMockResources.initProductCatalog(new ProductCatalog());
        orderCatalog = InitMockResources.initOrderCatalog(new OrderCatalog());
        cart = new Cart();
    }

    private void initRepository() {
        productRepository = new ProductRepositoryImpl(productCatalog);
        orderRepository = new OrderRepositoryImpl(orderCatalog);
        cartRepository = new CartRepositoryImpl(cart);
    }

    private void initService() {
        productService = new ProductServiceImpl(productRepository);
        orderService = new OrderServiceImpl(orderRepository);
        cartService = new CartServiceImpl(productRepository, orderRepository, cartRepository);
    }

    private void initController() {
        commandContainer = initCommands(new CommandContainer(), productService, orderService, cartService);
    }

    private static CommandContainer initCommands(CommandContainer commandContainer,
                                     ProductService productService, OrderService orderService, CartService cartService) {
        // full cast
        commandContainer.getCommands().put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.getCommands().put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(cartService));
        commandContainer.getCommands().put(ViewCartCmd.FULL_KEY, new ViewCartCmd(cartService));
        commandContainer.getCommands().put(CheckoutCmd.FULL_KEY, new CheckoutCmd(cartService));
        commandContainer.getCommands().put(ViewLatestProductsFromCartCmd.FULL_KEY, new ViewLatestProductsFromCartCmd(cartService));

        commandContainer.getCommands().put(ViewOrdersFromToByDateCmd.FULL_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.getCommands().put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.getCommands().put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.getCommands().put(HelpCmd.FULL_KEY, new HelpCmd());
        commandContainer.getCommands().put(StopCmd.FULL_KEY, new StopCmd());

        // short cast
        commandContainer.getCommands().put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.getCommands().put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService));
        commandContainer.getCommands().put(ViewLatestProductsFromCartCmd.SHORT_KEY, new ViewLatestProductsFromCartCmd(cartService));

        commandContainer.getCommands().put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.getCommands().put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.getCommands().put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService));

        return commandContainer;
    }
}
