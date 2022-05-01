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

    private ProductCatalog productCatalog;
    private OrderCatalog orderCatalog;
    private Cart cart;

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    private LocaleContainer localeContainer;
    private ResourceBundle resourceBundle;

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
        initLocale();
        initStrategyCreatingProduct();
        initService();
        initProductDataConsoleScanner();
        initProductDataConsoleScannerForCart();
        initController();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
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

    private void initLocale() {
        localeContainer = initLocaleContainer(new LocaleContainer());
        System.out.println(ShopLiterals.MSG_WHEN_INIT_LOCALE);
        localeContainer.viewExistingLocales();
        System.out.println(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        System.out.println(ShopLiterals.MSG_DEFAULT_LOCALE);
        while (true) {
            String strLocale = scanner.nextLine().strip().toLowerCase();
            if (localeContainer.isContainLocale(strLocale)) {
                resourceBundle = ResourceBundle.getBundle(NAME_RESOURCE_BUNDLE, localeContainer.getLocaleByKey(strLocale));
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_LOCALE,
                        resourceBundle.getLocale().getLanguage());
                return;
            } else if (strLocale.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    strLocale.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                resourceBundle = ResourceBundle.getBundle(NAME_RESOURCE_BUNDLE, Locale.ENGLISH);
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_LOCALE,
                        ShopLiterals.NATIVE_LOCALE_EN);
                return;
            } else {
                System.out.printf(ShopLiterals.MSG_INVALID_INPUT_LOCALE, strLocale);
                localeContainer.viewExistingLocales();
                System.out.println(ShopLiterals.TRY_AGAIN);
            }
        }
    }

    private void initStrategyCreatingProduct() {
        System.out.printf(ShopLiterals.MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY,
                ManualCreateCommodity.getFullDescription(), AutoCreateCommodity.getFullDescription());
        System.out.println(ShopLiterals.MSG_ABILITY_SKIP_OPERATION);
        System.out.println(ShopLiterals.MSG_DEFAULT_PRODUCT_CREATING_STRATEGY);
        while (true) {
            String command = scanner.nextLine().strip();
            if (command.equals(ManualCreateCommodity.CODE_KEY.toString()) ||
                    command.equals(ManualCreateCommodity.FULL_KEY) ||
                    command.equals(ManualCreateCommodity.SHORT_KEY)) {
                productCreatingContainer = initReflectManualProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(AutoCreateCommodity.CODE_KEY.toString()) ||
                    command.equals(AutoCreateCommodity.FULL_KEY) ||
                    command.equals(AutoCreateCommodity.SHORT_KEY)) {
                productCreatingContainer = initReflectAutoProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.AUTO_PRODUCT_CREATING_STRATEGY);
                return;
            } else if (command.equals(ShopLiterals.SKIP_CMD_FULL_CAST) ||
                    command.equals(ShopLiterals.SKIP_CMD_SHORT_CAST)) {
                productCreatingContainer = initReflectManualProductCreatingEntities(new ProductCreatingContainer());
                System.out.printf(ShopLiterals.MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY,
                        ShopLiterals.MANUAL_PRODUCT_CREATING_STRATEGY);
                return;
            } else {
                System.out.println(ShopLiterals.MSG_UNSUPPORTED_COMMAND);
                System.out.printf(ShopLiterals.MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY, command,
                        ManualCreateCommodity.getHelpFullDescription(), AutoCreateCommodity.getHelpFullDescription());
                System.out.println(ShopLiterals.TRY_AGAIN);
            }
        }
    }

    private void initProductDataConsoleScanner() {
        productDataConsoleScanner = new ProductDataConsoleScanner(productService, productCreatingContainer, resourceBundle);
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

    private ProductCreatingContainer initManualProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ManualCreateBook());
        productCreatingContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ManualCreateAudiobook());
        productCreatingContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ManualCreateEReader());

        return productCreatingContainer;
    }

    private ProductCreatingContainer initAutoProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new AutoCreateBook());
        productCreatingContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new AutoCreateAudiobook());
        productCreatingContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new AutoCreateEReader());

        return productCreatingContainer;
    }

    private ProductCreatingContainer initReflectManualProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ReflectManualCreateProduct(new Book()));
        productCreatingContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ReflectManualCreateProduct(new Audiobook()));
        productCreatingContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ReflectManualCreateProduct(new EReader()));

        return productCreatingContainer;
    }

    private ProductCreatingContainer initReflectAutoProductCreatingEntities(ProductCreatingContainer productCreatingContainer) {
        productCreatingContainer.put(ShopLiterals.BOOK_LITERAL_TYPE, new ReflectAutoCreateProduct(new Book()));
        productCreatingContainer.put(ShopLiterals.AUDIOBOOK_LITERAL_TYPE, new ReflectAutoCreateProduct(new Audiobook()));
        productCreatingContainer.put(ShopLiterals.E_READER_LITERAL_TYPE, new ReflectAutoCreateProduct(new EReader()));

        return productCreatingContainer;
    }

    private CommandContainer initCommands(CommandContainer commandContainer,
                                                 ProductService productService, OrderService orderService, CartService cartService) {
        // full cast
        commandContainer.put(ViewProductCatalogCmd.FULL_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.put(PutProductToCartCmd.FULL_KEY, new PutProductToCartCmd(cartService));
        commandContainer.put(ViewCartCmd.FULL_KEY, new ViewCartCmd(cartService));
        commandContainer.put(CheckoutCmd.FULL_KEY, new CheckoutCmd(cartService));
        commandContainer.put(ViewCartHistoryCmd.FULL_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.put(ViewOrdersFromToByDateCmd.FULL_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.put(ViewOrderCatalogCmd.FULL_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.put(ViewOrderByNearestDateCmd.FULL_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.put(CreateNewProductCmd.FULL_KEY, new CreateNewProductCmd(productService, resourceBundle));

        commandContainer.put(HelpCmd.FULL_KEY, new HelpCmd());
        commandContainer.put(CloseShopCmd.FULL_KEY, new CloseShopCmd(productService));

        // short cast
        commandContainer.put(ViewProductCatalogCmd.SHORT_KEY, new ViewProductCatalogCmd(productService));
        commandContainer.put(PutProductToCartCmd.SHORT_KEY, new PutProductToCartCmd(cartService));
        commandContainer.put(ViewCartHistoryCmd.SHORT_KEY, new ViewCartHistoryCmd(cartService));

        commandContainer.put(ViewOrdersFromToByDateCmd.SHORT_KEY, new ViewOrdersFromToByDateCmd(orderService));
        commandContainer.put(ViewOrderCatalogCmd.SHORT_KEY, new ViewOrderCatalogCmd(orderService));
        commandContainer.put(ViewOrderByNearestDateCmd.SHORT_KEY, new ViewOrderByNearestDateCmd(orderService));

        commandContainer.put(CreateNewProductCmd.SHORT_KEY, new CreateNewProductCmd(productService, resourceBundle));

        return commandContainer;
    }
}
