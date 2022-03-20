package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.util.List;

public interface ProductService extends EntityService {
    Commodity getProductById(Long id);
    List<Commodity> getAllProducts();
    boolean isContainProduct(Long id);
}
