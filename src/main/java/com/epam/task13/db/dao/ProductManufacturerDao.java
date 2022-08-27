package com.epam.task13.db.dao;

import com.epam.task13.entity.ProductManufacturer;

import java.util.List;

public interface ProductManufacturerDao {
    List<ProductManufacturer> getAll();
    ProductManufacturer getForId(int id);
}
