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
import com.epam.task4.util.DateConsoleScanner;
import com.epam.task4.util.ProductDataConsoleScannerForCart;
import com.epam.task6.create_product.ProductCreatingEntityContainer;
import com.epam.task6.create_product.ProductCreatingStrategyContainer;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateAudiobook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateBook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateEReader;
import com.epam.task6.create_product.strategy.auto_creating.AutoProductCreatingStrategy;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateAudiobook;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateBook;
import com.epam.task6.create_product.strategy.manual_creating.ManualCreateEReader;
import com.epam.task6.create_product.strategy.manual_creating.ManualProductCreatingStrategy;
import com.epam.task6.util.ProductDataConsoleScanner;
import com.epam.task6.util.ProductDataRandomGenerator;
import com.epam.task6.util.UtilProductCatalog;
import com.epam.task7.LocaleContainer;
import com.epam.task7.create_product.strategy.ReflectAutoCreateProduct;
import com.epam.task7.create_product.strategy.ReflectManualCreateProduct;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class AppContext {
    public static final String PATH_PRODUCT_CATALOG = "src/main/resources/product_catalog";
    public static final String NAME_RESOURCE_BUNDLE = "i18n/messages";

    private final Scanner scanner;

    // localization
    private ResourceBundle resourceBundle;
    private LocaleContainer localeContainer;

    // product creating strategy
    private ProductCreatingEntityContainer productCreatingEntityContainer;
    private ProductCreatingStrategyContainer productCreatingStrategyContainer;

    // data sources
    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    // repositories
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    // services
    private ProductService productService;
    private OrderService orderService;
    private CartService cartService;

    // console data scanners
    private DateConsoleScanner dateConsoleScanner;
    private ProductDataConsoleScanner productDataConsoleScanner;
    private ProductDataConsoleScannerForCart productDataConsoleScannerForCart;

    // random data generator
    private ProductDataRandomGenerator productDataRandomGenerator;

    // controller
    private CommandContainer commandContainer;

    public AppContext() {
        scanner = new Scanner(System.in);
        init();
    }

    public void init() {
        initLocale();
        initDataSource();
        initRepository();
        initService();
        initConsoleScanner();
        initDataGenerator();
        initProductCreatingStrategy();
        initController();
    }

    private void initLocale() {
        localeContainer = initLocaleContainer(new LocaleContainer());
        resourceBundle = ResourceBundle.getBundle(AppContext.NAME_RESOURCE_BUNDLE, Locale.ENGLISH);
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

    private void initService() {
        productService = new ProductServiceImpl(productRepository);
        orderService = new OrderServiceImpl(orderRepository);
        cartService = new CartServiceImpl(productRepository, orderRepository, cartRepository);
    }

    private void initConsoleScanner() {
        initDateConsoleScanner();
        initProductDataConsoleScanner();
        initProductDataConsoleScannerForCart();
    }

    private void initDateConsoleScanner() {
        dateConsoleScanner = new DateConsoleScanner(scanner);
    }

    private void initProductDataConsoleScanner() {
        productDataConsoleScanner = new ProductDataConsoleScanner(productService, scanner, null);
    }

    private void initProductDataConsoleScannerForCart() {
        productDataConsoleScannerForCart = new ProductDataConsoleScannerForCart(productService, cartService, scanner);
    }

    private void initDataGenerator() {
        productDataRandomGenerator = new ProductDataRandomGenerator(productService);
    }

    private LocaleContainer initLocaleContainer(LocaleContainer localeContainer) {
        Locale enLocale = new Locale(ShopLiterals.SHORT_LOCALE_EN);
        Locale ruLocale = new Locale(ShopLiterals.SHORT_LOCALE_RU);

        localeContainer.put(ShopLiterals.FULL_LOCALE_EN, enLocale);
        localeContainer.put(ShopLiterals.SHORT_LOCALE_EN, enLocale);
        localeContainer.put(ShopLiterals.NATIVE_LOCALE_EN, enLocale);

        localeContainer.put(ShopLiterals.FULL_LOCALE_RU, ruLocale);
        localeContainer.put(ShopLiterals.SHORT_LOCALE_RU, ruLocale);
        localeContainer.put(ShopLiterals.NATIVE_LOCALE_RU, ruLocale);

        return localeContainer;
    }

    private void initProductCreatingStrategy() {
        productCreatingEntityContainer = initReflectManualProductCreatingEntities(new ProductCreatingEntityContainer(new ManualProductCreatingStrategy()));
        // pulling dependencies between classes ProductCreatingEntityContainer and ProductCreatingEntityContainer
        productDataConsoleScanner.setProductCreatingEntityContainer(productCreatingEntityContainer);
        productCreatingStrategyContainer = initProductCreatingStrategiesContainer(new ProductCreatingStrategyContainer());
    }

    private ProductCreatingStrategyContainer initProductCreatingStrategiesContainer(ProductCreatingStrategyContainer productCreatingStrategyContainer) {
        ProductCreatingEntityContainer manualProductCreatingEntityContainer = productCreatingEntityContainer; // default product creating strategy
        ProductCreatingEntityContainer autoProductCreatingEntityContainer = initReflectAutoProductCreatingEntities(
                new ProductCreatingEntityContainer(new AutoProductCreatingStrategy()));

        productCreatingStrategyContainer.put(ManualProductCreatingStrategy.CODE_KEY.toString(), manualProductCreatingEntityContainer);
        productCreatingStrategyContainer.put(ManualProductCreatingStrategy.FULL_KEY, manualProductCreatingEntityContainer);
        productCreatingStrategyContainer.put(ManualProductCreatingStrategy.SHORT_KEY, manualProductCreatingEntityContainer);

        productCreatingStrategyContainer.put(AutoProductCreatingStrategy.CODE_KEY.toString(), autoProductCreatingEntityContainer);
        productCreatingStrategyContainer.put(AutoProductCreatingStrategy.FULL_KEY, autoProductCreatingEntityContainer);
        productCreatingStrategyContainer.put(AutoProductCreatingStrategy.SHORT_KEY, autoProductCreatingEntityContainer);

        return productCreatingStrategyContainer;
    }

    private ProductCreatingEntityContainer initManualProductCreatingEntities(ProductCreatingEntityContainer productCreatingEntityContainer) {
        productCreatingEntityContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ManualCreateBook(productDataConsoleScanner));
        productCreatingEntityContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ManualCreateAudiobook(productDataConsoleScanner));
        productCreatingEntityContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ManualCreateEReader(productDataConsoleScanner));

        return productCreatingEntityContainer;
    }

    private ProductCreatingEntityContainer initAutoProductCreatingEntities(ProductCreatingEntityContainer productCreatingEntityContainer) {
        productCreatingEntityContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new AutoCreateBook(productDataRandomGenerator));
        productCreatingEntityContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new AutoCreateAudiobook(productDataRandomGenerator));
        productCreatingEntityContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new AutoCreateEReader(productDataRandomGenerator));

        return productCreatingEntityContainer;
    }

    private ProductCreatingEntityContainer initReflectManualProductCreatingEntities(ProductCreatingEntityContainer productCreatingEntityContainer) {
        productCreatingEntityContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ReflectManualCreateProduct(new Book(), productDataConsoleScanner));
        productCreatingEntityContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ReflectManualCreateProduct(new Audiobook(), productDataConsoleScanner));
        productCreatingEntityContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ReflectManualCreateProduct(new EReader(), productDataConsoleScanner));

        return productCreatingEntityContainer;
    }

    private ProductCreatingEntityContainer initReflectAutoProductCreatingEntities(ProductCreatingEntityContainer productCreatingEntityContainer) {
        productCreatingEntityContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ReflectAutoCreateProduct(new Book(), productDataRandomGenerator));
        productCreatingEntityContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ReflectAutoCreateProduct(new Audiobook(), productDataRandomGenerator));
        productCreatingEntityContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ReflectAutoCreateProduct(new EReader(), productDataRandomGenerator));

        return productCreatingEntityContainer;
    }

    private void initController() {
        commandContainer = initCommands(new CommandContainer(), productService, orderService, cartService);
    }

    private CommandContainer initCommands(CommandContainer commandContainer,
                                          ProductService productService, OrderService orderService, CartService cartService) {
        // full cast
        commandContainer.put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(cartService, productDataConsoleScannerForCart));
        commandContainer.put(ViewCartCmd.FULL_KEY, new ViewCartCmd(cartService));
        commandContainer.put(CheckoutCmd.FULL_KEY, new CheckoutCmd(cartService));
        commandContainer.put(ViewCartHistoryCmd.FULL_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.put(ViewOrdersFromToByDateCmd.FULL_KEY, new ViewOrdersFromToByDateCmd(orderService, dateConsoleScanner));
        commandContainer.put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(orderService, dateConsoleScanner));

        commandContainer.put(CreateNewProductCmd.FULL_KEY, new CreateNewProductCmd(productService, productDataConsoleScanner));

        commandContainer.put(HelpCmd.FULL_KEY, new HelpCmd());
        commandContainer.put(CloseShopCmd.FULL_KEY, new CloseShopCmd(productService));

        // short cast
        commandContainer.put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService, productDataConsoleScannerForCart));
        commandContainer.put(ViewCartHistoryCmd.SHORT_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService, dateConsoleScanner));
        commandContainer.put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService, dateConsoleScanner));

        commandContainer.put(CreateNewProductCmd.SHORT_KEY, new CreateNewProductCmd(productService, productDataConsoleScanner));

        return commandContainer;
    }

    public void setShopSettings() {
        setLocale();
        setProductCreatingStrategy();
    }

    private void setLocale() {
        MainApp.printMessage(ShopLiterals.MSG_WHEN_INIT_LOCALE);
        MainApp.printMessage(ShopLiterals.MSG_EXISTING_LOCALES);
        localeContainer.viewExistingLocales();
        MainApp.printMessage(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        MainApp.printMessage(ShopLiterals.MSG_DEFAULT_LOCALE);
        while (true) {
            String strLocale = scanner.nextLine().strip().toLowerCase();
            if (localeContainer.isContainLocale(strLocale)) {
                resourceBundle = ResourceBundle.getBundle(AppContext.NAME_RESOURCE_BUNDLE, localeContainer.getLocaleByKey(strLocale));
                MainApp.printSuccessMessage(ShopLiterals.MSG_SUCCESS_SET_LOCALE, resourceBundle.getLocale().getLanguage());
                return;
            } else if (strLocale.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    strLocale.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                MainApp.printSuccessMessage(ShopLiterals.MSG_SUCCESS_SET_LOCALE, resourceBundle.getLocale().getLanguage()); // ShopLiterals.NATIVE_LOCALE_EN
                return;
            } else {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_INPUT_LOCALE, strLocale);
                MainApp.printWarning(ShopLiterals.MSG_EXISTING_LOCALES);
                localeContainer.viewExistingLocales();
                MainApp.printWarning(ShopLiterals.PLEASE_TRY_AGAIN);
            }
        }
    }

    private void setProductCreatingStrategy() {
        MainApp.printMessage(ShopLiterals.MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY);
        MainApp.printMessage(ShopLiterals.MSG_EXISTING_PRODUCT_CREATING_STRATEGIES);
        productCreatingStrategyContainer.viewExistingProductCreatingStrategies();
        MainApp.printMessage(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        MainApp.printMessage(ShopLiterals.MSG_DEFAULT_PRODUCT_CREATING_STRATEGY);
        while (true) {
            String command = scanner.nextLine().strip();
            if (productCreatingStrategyContainer.isContainStrategy(command)) {
                productCreatingEntityContainer = productCreatingStrategyContainer.getEntityContainerByKey(command);
                productDataConsoleScanner.setProductCreatingEntityContainer(productCreatingEntityContainer);
                MainApp.printSuccessMessage(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        productCreatingEntityContainer.getProductCreatingStrategy().getName());
                return;
            } else if (command.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    command.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                MainApp.printSuccessMessage(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        productCreatingEntityContainer.getProductCreatingStrategy().getName()); // ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY
                return;
            } else {
                MainApp.printWarning(ShopLiterals.MSG_UNSUPPORTED_COMMAND);
                MainApp.printWarning(ShopLiterals.MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY, command);
                productCreatingStrategyContainer.viewExistingProductCreatingStrategies();
                MainApp.printWarning(ShopLiterals.PLEASE_TRY_AGAIN);
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
