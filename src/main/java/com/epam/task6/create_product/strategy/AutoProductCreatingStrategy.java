package com.epam.task6.create_product.strategy;

import com.epam.task4.constants.ShopLiterals;

/**
 * Auto random generation creating product
 *
 * @author Oleksii Kushch
 */
public class AutoProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 2;

    public static final String FULL_KEY = "--automatic";
    public static final String SHORT_KEY = "-a";

    public static final String DESCRIPTION = "Automatic mode using random generation";

    public static final int MAX_VALUE_RANDOM_GENERATED_NUM = 100;

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
