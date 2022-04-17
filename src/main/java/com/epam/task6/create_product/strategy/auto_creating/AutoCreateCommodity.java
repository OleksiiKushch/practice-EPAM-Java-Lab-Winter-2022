package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.CreateProduct;

import java.math.BigDecimal;
import java.security.SecureRandom;

public abstract class AutoCreateCommodity implements CreateProduct {
    public static final Integer CODE_KEY = 2;
    public static final String FULL_KEY = "--automatic";
    public static final String SHORT_KEY = "-a";

    public static final String DESCRIPTION = "Automatic mode using random generation";

    public static final int MAX_VALUE_RANDOM_GENERATED_NUM = 100;

    private static final String FRONT_TITLE = "FrontTitle";

    private final Commodity commodity;

    protected AutoCreateCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    @Override
    public Commodity create() {
        ProductService productService = MainApp.getContext().getProductService();
        SecureRandom secureRandom = new SecureRandom();

        long id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productService.getProductById(id) != null) {
            id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        commodity.setId(id);
        commodity.setFrontTitle(FRONT_TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        commodity.setPrice(new BigDecimal(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM)));
        commodity.setAmount(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));

        return commodity;
    }

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }
}
