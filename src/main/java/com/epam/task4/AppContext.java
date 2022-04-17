package com.epam.task4;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.EReader;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.CommandContainer;
import com.epam.task4.controller.command.CheckoutCmd;
import com.epam.task4.controller.command.CloseShopCmd;
import com.epam.task4.controller.command.CreateNewProductCmd;
import com.epam.task4.controller.command.HelpCmd;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.controller.command.ViewCartCmd;
import com.epam.task4.controller.command.ViewCartHistoryCmd;
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
import com.epam.task4.util.ProductDataConsoleScannerForCart;
import com.epam.task6.create_product.ProductCreatingContainer;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateAudiobook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateBook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateCommodity;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateEReader;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateAudiobook;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateBook;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateCommodity;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateEReader;
import com.epam.task6.util.ProductDataConsoleScanner;
import com.epam.task6.util.UtilProductCatalog;

import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    public static final String PATH_PRODUCT_CATALOG = "src/main/resources/product_catalog";

    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    private ProductCreatingContainer productCreatingContainer;

    private ProductService productService;
    private OrderService orderService;
    private CartService cartService;

    private CommandContainer commandContainer;

    private final Scanner scanner;

    private ProductDataConsoleScanner productDataConsoleScanner;

    private ProductDataConsoleScannerForCart productDataConsoleScannerForCart;

    public AppContext() {
        scanner = new Scanner(System.in);
        initDataSource();
        initRepository();
        initStrategyCreatingProduct();
        initService();
        initProductDataConsoleScanner();
        initProductDataConsoleScannerForCart();
        initController();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ProductDataConsoleScanner getProductDataConsoleScanner() {
        return productDataConsoleScanner;
    }

    public ProductDataConsoleScannerForCart getProductDataConsoleScannerForCart() {
        return productDataConsoleScannerForCart;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    public ProductService getProductService() {
        return productService;
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
                ManualCreateCommodity.getFullDescription(), AutoCreateCommodity.getFullDescription());
        System.out.println(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        System.out.println(ShopLiterals.MSG_DEFAULT_PRODUCT_CREATING_STRATEGY);
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals(ManualCreateCommodity.CODE_KEY.toString()) ||
                    command.equals(ManualCreateCommodity.FULL_KEY) ||
                    command.equals(ManualCreateCommodity.SHORT_KEY)) {
                productCreatingContainer = initManualProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(AutoCreateCommodity.CODE_KEY.toString()) ||
                    command.equals(AutoCreateCommodity.FULL_KEY) ||
                    command.equals(AutoCreateCommodity.SHORT_KEY)) {
                productCreatingContainer = initAutoProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.AUTO_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    command.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                productCreatingContainer = initManualProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else {
                System.out.println(ShopLiterals.MSG_UNSUPPORTED_COMMAND);
                System.out.printf(ShopLiterals.MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY, command,
                        ManualCreateCommodity.getHelpFullDescription(), AutoCreateCommodity.getHelpFullDescription());
            }
        }
    }

    private void initProductDataConsoleScanner() {
        productDataConsoleScanner = new ProductDataConsoleScanner(productService, productCreatingContainer);
    }

    private void initProductDataConsoleScannerForCart() {
        productDataConsoleScannerForCart = new ProductDataConsoleScannerForCart(productService, cartService);
    }

    private void initService() {
        productService = new ProductServiceImpl(productRepository);
        orderService = new OrderServiceImpl(orderRepository);
        cartService = new CartServiceImpl(productRepository, orderRepository, cartRepository);
    }

    private void initController() {
        commandContainer = initCommands(new CommandContainer(), productService, orderService, cartService);
    }

    private static ProductCreatingContainer initManualProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.getCommands().put(ShopLiterals.BOOK_LITERAL_TYPE, new ManualCreateBook(new Book()));
        productCreatingContainer.getCommands().put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ManualCreateAudiobook(new Audiobook()));
        productCreatingContainer.getCommands().put(ShopLiterals.E_READER_LITERAL_TYPE, new ManualCreateEReader(new EReader()));

        return productCreatingContainer;
    }

    private static ProductCreatingContainer initAutoProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.getCommands().put(ShopLiterals.BOOK_LITERAL_TYPE, new AutoCreateBook(new Book()));
        productCreatingContainer.getCommands().put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new AutoCreateAudiobook(new Audiobook()));
        productCreatingContainer.getCommands().put(ShopLiterals.E_READER_LITERAL_TYPE, new AutoCreateEReader(new EReader()));

        return productCreatingContainer;
    }

    private static CommandContainer initCommands(CommandContainer commandContainer,
                                                 ProductService productService, OrderService orderService, CartService cartService) {
        // full cast
        commandContainer.getCommands().put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.getCommands().put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(cartService));
        commandContainer.getCommands().put(ViewCartCmd.FULL_KEY, new ViewCartCmd(cartService));
        commandContainer.getCommands().put(CheckoutCmd.FULL_KEY, new CheckoutCmd(cartService));
        commandContainer.getCommands().put(ViewCartHistoryCmd.FULL_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.getCommands().put(ViewOrdersFromToByDateCmd.FULL_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.getCommands().put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.getCommands().put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.getCommands().put(CreateNewProductCmd.FULL_KEY, new CreateNewProductCmd(productService));

        commandContainer.getCommands().put(HelpCmd.FULL_KEY, new HelpCmd());
        commandContainer.getCommands().put(CloseShopCmd.FULL_KEY, new CloseShopCmd(productService));

        // short cast
        commandContainer.getCommands().put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.getCommands().put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService));
        commandContainer.getCommands().put(ViewCartHistoryCmd.SHORT_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.getCommands().put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.getCommands().put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.getCommands().put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.getCommands().put(CreateNewProductCmd.SHORT_KEY, new CreateNewProductCmd(productService));

        return commandContainer;
    }
}
