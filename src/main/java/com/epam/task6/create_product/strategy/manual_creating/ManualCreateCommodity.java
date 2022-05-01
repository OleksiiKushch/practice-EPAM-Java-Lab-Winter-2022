package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * @author Oleksii Kushch
 */
public abstract class ManualCreateCommodity implements CreateProduct {
    public static final Integer CODE_KEY = 1;

    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String DESCRIPTION = "Manual mode via console";

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Commodity commodity = new Commodity();

        commodity.setId(productDataConsoleScanner.inputProductId());
        commodity.setFrontTitle(productDataConsoleScanner.inputProductFrontTitle());
        commodity.setPrice(productDataConsoleScanner.inputProductPrice());
        commodity.setAmount((productDataConsoleScanner.inputProductAmount()));

        return commodity;
    }

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
