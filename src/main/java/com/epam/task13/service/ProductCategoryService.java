package com.epam.task13.service;

import com.epam.task13.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAll();
    ProductCategory getById(int id);
}
