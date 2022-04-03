package com.epam.task6.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;

public interface ProductCreatingStrategy {
    Commodity createProduct(ProductRepository productRepository);
}
