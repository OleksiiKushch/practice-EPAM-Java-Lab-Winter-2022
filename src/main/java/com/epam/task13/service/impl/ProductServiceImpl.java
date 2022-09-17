package com.epam.task13.service.impl;

import com.epam.task13.db.dao.ProductDao;
import com.epam.task13.entity.product.Product;
import com.epam.task13.service.ProductCategoryService;
import com.epam.task13.service.ProductManufacturerService;
import com.epam.task13.service.ProductService;
import com.epam.task13.util.PagePaginationData;
import com.epam.task13.util.ProductFilterFormBean;
import com.epam.task13.util.SortingData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LogManager.getLogger(ProductServiceImpl.class);

    private final ProductDao productDao;
    private final ProductManufacturerService productManufacturerService;
    private final ProductCategoryService productCategoryService;

    public ProductServiceImpl(ProductDao productDao, ProductManufacturerService productManufacturerService, ProductCategoryService productCategoryService) {
        this.productDao = productDao;
        this.productManufacturerService = productManufacturerService;
        this.productCategoryService = productCategoryService;
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductForId(id);
    }

    @Override
    public List<Product> findProducts(ProductFilterFormBean productFilterFormBean, SortingData sortingData, PagePaginationData pagePaginationData) {
        List<Product> result = productDao.getProductsWithPaginationSortingFiltration(productFilterFormBean, sortingData, pagePaginationData);
        for (Product product : result) {
            product.setManufacturer(productManufacturerService.getById(product.getManufacturer().getId()));
            product.setCategory(productCategoryService.getById(product.getCategory().getId()));
        }
        return result;
    }

    @Override
    public int getCountOfProductsWithFiltering(ProductFilterFormBean productFilterFormBean) {
        return productDao.getCountOfProductsWithFiltering(productFilterFormBean);
    }

    @Override
    public BigDecimal getMinProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) {
        return productDao.getMinProductPriceWithFiltering(productFilterFormBean);
    }

    @Override
    public BigDecimal getMaxProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) {
        return productDao.getMaxProductPriceWithFiltering(productFilterFormBean);
    }
}
