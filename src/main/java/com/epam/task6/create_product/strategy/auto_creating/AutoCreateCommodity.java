package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataRandomGenerator;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateCommodity implements CreateProduct {
    protected final ProductDataRandomGenerator productDataRandomGenerator;

    public AutoCreateCommodity(ProductDataRandomGenerator productDataRandomGenerator) {
        this.productDataRandomGenerator = productDataRandomGenerator;
    }

    @Override
    public Commodity create() {
        Commodity commodity = new Commodity();

        commodity.setId(productDataRandomGenerator.getRandomProductId());
        commodity.setFrontTitle(productDataRandomGenerator.getRandomProductFrontTitle());
        commodity.setPrice(productDataRandomGenerator.getRandomProductPrice());
        commodity.setAmount(productDataRandomGenerator.getRandomProductAmount());

        return commodity;
    }
}
