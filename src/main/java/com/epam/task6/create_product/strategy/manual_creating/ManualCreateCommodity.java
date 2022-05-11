package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * @author Oleksii Kushch
 */
public class ManualCreateCommodity implements CreateProduct {
    protected final ProductDataConsoleScanner productDataConsoleScanner;

    public ManualCreateCommodity(ProductDataConsoleScanner productDataConsoleScanner) {
        this.productDataConsoleScanner = productDataConsoleScanner;
    }

    @Override
    public Commodity create() {
        Commodity commodity = new Commodity();

        commodity.setId(productDataConsoleScanner.inputProductId());
        commodity.setFrontTitle(productDataConsoleScanner.inputProductFrontTitle());
        commodity.setPrice(productDataConsoleScanner.inputProductPrice());
        commodity.setAmount((productDataConsoleScanner.inputProductAmount()));

        return commodity;
    }
}
