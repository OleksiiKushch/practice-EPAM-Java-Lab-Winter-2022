package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ConsoleColor;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.service.ProductService;
import com.epam.task6.util.ProductDataConsoleScanner;

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
    public void addProductToCatalog() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Commodity newProduct = productDataConsoleScanner.inputCreateProductType().create();

        productRepository.insert(newProduct);
        System.out.println(ShopLiterals.MSG_ADD_NEW_PRODUCT_TO_CATALOG_SUCCESS);
        System.out.println(ConsoleColor.GREEN + newProduct + ConsoleColor.RESET);
    }

    @Override
    public Commodity getProductById(Long id) {
        return productRepository.getById(id);
    }
}
