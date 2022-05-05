package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.service.ProductService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Commodity> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public void addProductToCatalog(Commodity product) {
        productRepository.insert(product);
    }

    @Override
    public Commodity getProductById(Long id) {
        return productRepository.getById(id);
    }
}
