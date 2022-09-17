package com.epam.task13.service.impl;

import com.epam.task13.db.dao.ProductCategoryDao;
import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.entity.product.ProductCategory;
import com.epam.task13.service.ProductCategoryService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {
    private static final Logger LOG = LogManager.getLogger(ProductCategoryServiceImpl.class);

    private final ProductCategoryDao productCategoryDao;
    private final ProductDao productDao;

    public ProductCategoryServiceImpl(ProductCategoryDao productCategoryDao, ProductDao productDao) {
        this.productCategoryDao = productCategoryDao;
        this.productDao = productDao;
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> result = productCategoryDao.getAll();
        for (ProductCategory category : result) {
            category.setCount(productDao.getCountForCategoryId(category.getId()));
        }
        return result;
    }

    @Override
    public ProductCategory getById(int id) {
        return productCategoryDao.getForId(id);
    }
}
