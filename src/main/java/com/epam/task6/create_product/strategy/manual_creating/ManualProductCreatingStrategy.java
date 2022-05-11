package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.ProductCreatingStrategy;

public class ManualProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 1;

    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String NAME = "manual";

    public static final String DESCRIPTION = "Manual mode via console";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getFullView() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
