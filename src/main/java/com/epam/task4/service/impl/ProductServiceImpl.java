package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.service.ProductService;
import com.epam.task6.strategy.create_product.ProductCreatingStrategy;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCreatingStrategy productCreatingStrategy;

    public ProductServiceImpl(ProductRepository productRepository, ProductCreatingStrategy productCreatingStrategy) {
        this.productRepository = productRepository;
        this.productCreatingStrategy = productCreatingStrategy;
    }

    @Override
    public List<Commodity> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public void addProductToCatalog() {
        productRepository.insert(productCreatingStrategy.createProduct(productRepository));
    }
}
