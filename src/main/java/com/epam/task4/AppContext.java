package com.epam.task4;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.CommandContainer;
import com.epam.task4.controller.command.AddProductToCatalogCmd;
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
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;
import com.epam.task6.create_product.strategy.ManualProductCreatingStrategy;
import com.epam.task6.create_product.strategy.ProductCreatingStrategy;
import com.epam.task4.util.UtilProductCatalog;

import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    public static final String PATH_PRODUCT_CATALOG = "src/main/java/com/epam/task4/product_catalog";

    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    /** set the default mode as "manual", however it can be changed in the method {@link #initStrategyCreatingProduct()} */
    private ProductCreatingStrategy productCreatingStrategy = new ManualProductCreatingStrategy();

    private ProductService productService;
    private OrderService orderService;
    private CartService cartService;

    private CommandContainer commandContainer;

    private final Scanner scanner;

    public AppContext() {
        scanner = new Scanner(System.in);
        initDataSource();
        initRepository();
        initStrategyCreatingProduct();
        initService();
        initController();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    private void initDataSource() {
        productCatalog = UtilProductCatalog.loadProductCatalog(PATH_PRODUCT_CATALOG);
        orderCatalog = InitMockResources.initOrderCatalog(new OrderCatalog());
        cart = new Cart();
    }

    private void initRepository() {
        productRepository = new ProductRepositoryImpl(productCatalog);
        orderRepository = new OrderRepositoryImpl(orderCatalog);
        cartRepository = new CartRepositoryImpl(cart);
    }

    private void initStrategyCreatingProduct() {
        System.out.printf(ShopLiterals.MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY,
                ManualProductCreatingStrategy.getFullDescription(), AutoProductCreatingStrategy.getFullDescription());
        System.out.println(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        System.out.println(ShopLiterals.MSG_DEFAULT_PRODUCT_CREATING_STRATEGY);
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals(ManualProductCreatingStrategy.CODE_KEY.toString()) ||
                    command.equals(ManualProductCreatingStrategy.FULL_KEY) ||
                    command.equals(ManualProductCreatingStrategy.SHORT_KEY)) {
                productCreatingStrategy = new ManualProductCreatingStrategy();
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(AutoProductCreatingStrategy.CODE_KEY.toString()) ||
                    command.equals(AutoProductCreatingStrategy.FULL_KEY) ||
                    command.equals(AutoProductCreatingStrategy.SHORT_KEY)) {
                productCreatingStrategy = new AutoProductCreatingStrategy();
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.AUTO_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    command.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else {
                System.out.println(ShopLiterals.MSG_UNSUPPORTED_COMMAND);
                System.out.printf(ShopLiterals.MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY, command,
                        ManualProductCreatingStrategy.getHelpFullDescription(), AutoProductCreatingStrategy.getHelpFullDescription());
            }
        }
    }

    private void initService() {
        productService = new ProductServiceImpl(productRepository, productCreatingStrategy);
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

        commandContainer.getCommands().put(AddProductToCatalogCmd.FULL_KEY, new AddProductToCatalogCmd(productService));

        commandContainer.getCommands().put(HelpCmd.FULL_KEY, new HelpCmd());
        commandContainer.getCommands().put(StopCmd.FULL_KEY, new StopCmd());

        // short cast
        commandContainer.getCommands().put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.getCommands().put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService));
        commandContainer.getCommands().put(ViewLatestProductsFromCartCmd.SHORT_KEY, new ViewLatestProductsFromCartCmd(cartService));

        commandContainer.getCommands().put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.getCommands().put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.getCommands().put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.getCommands().put(AddProductToCatalogCmd.SHORT_KEY, new AddProductToCatalogCmd(productService));

        return commandContainer;
    }
}
