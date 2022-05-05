package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public interface ProductService {
    List<Commodity> getAllProducts();
    void addProductToCatalog(Commodity product);
    Commodity getProductById(Long id);
}
