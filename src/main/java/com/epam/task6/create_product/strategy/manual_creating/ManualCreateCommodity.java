package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.math.BigDecimal;

public abstract class ManualCreateCommodity implements CreateProduct {
    public static final Integer CODE_KEY = 1;
    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String DESCRIPTION = "Manual mode via console";

    protected Commodity commodity;

    protected ManualCreateCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Long id = productDataConsoleScanner.inputId();
        if (id == null) {
            return null;
        }
        commodity.setId(id);

        String frontTitle = productDataConsoleScanner.inputFrontTitle();
        if (frontTitle == null) {
            return null;
        }
        commodity.setFrontTitle(frontTitle);

        BigDecimal price = productDataConsoleScanner.inputPrice();
        if (price == null) {
            return null;
        }
        commodity.setPrice(price);

        Integer amount = productDataConsoleScanner.inputAmount();
        if (amount == null) {
            return null;
        }
        commodity.setAmount(amount);

        return commodity;
    }

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
