package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ConsoleColor;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.strategy.ProductCreatingStrategy;

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
        System.out.println(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);
        Commodity newProduct = productCreatingStrategy.createProduct(productRepository);
        if (newProduct == null) {
            System.out.println(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }
        productRepository.insert(newProduct);
        System.out.println(ShopLiterals.MSG_ADD_NEW_PRODUCT_TO_CATALOG_SUCCESS);
        System.out.println(ConsoleColor.GREEN + newProduct + ConsoleColor.RESET);
    }
}
