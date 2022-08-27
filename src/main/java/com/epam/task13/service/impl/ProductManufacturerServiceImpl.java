package com.epam.task13.service.impl;

import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.db.dao.ProductManufacturerDao;
import com.epam.task13.entity.ProductManufacturer;
import com.epam.task13.service.ProductManufacturerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductManufacturerServiceImpl implements ProductManufacturerService {
    private static final Logger LOG = LogManager.getLogger(ProductManufacturerServiceImpl.class);

    private final ProductManufacturerDao productManufacturerDao;
    private final ProductDao productDao;

    public ProductManufacturerServiceImpl(ProductManufacturerDao productManufacturerDao, ProductDao productDao) {
        this.productManufacturerDao = productManufacturerDao;
        this.productDao = productDao;
    }

    @Override
    public List<ProductManufacturer> getAll() {
        List<ProductManufacturer> result = productManufacturerDao.getAll();
        for (ProductManufacturer manufacturer : result) {
            manufacturer.setCount(productDao.getCountForManufacturerId(manufacturer.getId()));
        }
        return result;
    }

    @Override
    public ProductManufacturer getById(int id) {
        return productManufacturerDao.getForId(id);
    }
}
