package com.epam.task13.db.dao;

import com.epam.task13.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> getAll();
    ProductCategory getForId(int id);
}
