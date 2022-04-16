package com.epam.task6.create_product.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;

import java.math.BigDecimal;
import java.security.SecureRandom;

public abstract class AutoCreateCommodity implements CreateProduct {
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

        long id = secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productService.getProductById(id) != null) {
            id = secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        commodity.setId(id);
        commodity.setFrontTitle(FRONT_TITLE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        commodity.setPrice(new BigDecimal(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM)));
        commodity.setAmount(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));

        return commodity;
    }
}
