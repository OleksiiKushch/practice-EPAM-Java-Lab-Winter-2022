package com.epam.task13.service;

import com.epam.task13.entity.product.ProductManufacturer;

import java.util.List;

public interface ProductManufacturerService {
    List<ProductManufacturer> getAll();
    ProductManufacturer getById(int id);
}
