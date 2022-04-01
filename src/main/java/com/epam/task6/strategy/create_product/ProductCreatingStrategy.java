package com.epam.task6.strategy.create_product;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;

public interface ProductCreatingStrategy {
    Commodity createProduct(ProductRepository productRepository);
}
