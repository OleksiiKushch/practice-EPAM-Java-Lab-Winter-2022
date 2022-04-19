package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ManualCreateCommodity implements CreateProduct {
    public static final Integer CODE_KEY = 1;

    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String DESCRIPTION = "Manual mode via console";

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Commodity commodity = new Commodity();

        if (setField(commodity::setId, productDataConsoleScanner::inputId)) return null;
        if (setField(commodity::setFrontTitle, productDataConsoleScanner::inputFrontTitle)) return null;
        if (setField(commodity::setPrice, productDataConsoleScanner::inputPrice)) return null;
        if (setField(commodity::setAmount, productDataConsoleScanner::inputAmount)) return null;

        return commodity;
    }

    protected <T> boolean setField(Consumer<T> setter, Supplier<T> data) {
        T fieldValue = data.get();
        if (fieldValue == null) {
            return true;
        }
        setter.accept(fieldValue);
        return false;
    }

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
