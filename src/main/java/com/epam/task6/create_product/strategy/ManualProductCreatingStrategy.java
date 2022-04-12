package com.epam.task6.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.repository.ProductRepository;
import com.epam.task6.create_product.mirror_wrapper_entity.InitCommodity;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * Manual console (with validation logic) creating product
 *
 * @author Oleksii Kushch
 */
public class ManualProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 1;

    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String DESCRIPTION = "Manual mode via console";

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    @Override
    public Commodity createProduct(ProductRepository productRepository) {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        InitCommodity result = productDataConsoleScanner.inputType();
        if (result == null) {
            return null;
        }

        return result.initViaConsole(result.getCommodity(), productDataConsoleScanner);
    }
}
