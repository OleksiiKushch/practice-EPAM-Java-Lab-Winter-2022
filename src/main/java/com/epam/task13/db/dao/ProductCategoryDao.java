package com.epam.task13.db.dao;

import com.epam.task13.entity.product.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> getAll();
    ProductCategory getForId(int id);
}
