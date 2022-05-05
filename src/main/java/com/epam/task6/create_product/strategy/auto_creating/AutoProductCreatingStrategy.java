package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.ProductCreatingStrategy;

public class AutoProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 2;

    public static final String FULL_KEY = "--automatic";
    public static final String SHORT_KEY = "-a";

    public static final String NAME = "automatic";

    public static final String DESCRIPTION = "Automatic mode using random generation";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getFullView() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
