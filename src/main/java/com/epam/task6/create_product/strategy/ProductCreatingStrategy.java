package com.epam.task6.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;

public interface ProductCreatingStrategy {
    default Commodity createProduct() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        CreateProduct createProduct = productDataConsoleScanner.inputCreateProductType();
        if (createProduct == null) {
            return null;
        }

        return createProduct.create();
    }
}
