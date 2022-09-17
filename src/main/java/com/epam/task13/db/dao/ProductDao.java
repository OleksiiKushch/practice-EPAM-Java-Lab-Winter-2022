package com.epam.task13.db.dao;

import com.epam.task13.entity.product.Product;
import com.epam.task13.util.PagePaginationData;
import com.epam.task13.util.ProductFilterFormBean;
import com.epam.task13.util.SortingData;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    int insert(Product product) throws DaoException;
    Product getProductForId(int id) throws DaoException;
    int getCount() throws DaoException;
    List<Product> getAll() throws DaoException;
    List<Product> getProductsWithPaginationSortingFiltration(ProductFilterFormBean productFilterFormBean, SortingData sortingData, PagePaginationData pagePaginationData) throws DaoException;
    int getCountOfProductsWithFiltering(ProductFilterFormBean productFilterFormBean) throws DaoException;
    int getCountForCategoryId(int categoryId) throws DaoException;
    int getCountForManufacturerId(int manufacturerId) throws DaoException;
    BigDecimal getMinProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) throws DaoException;
    BigDecimal getMaxProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean) throws DaoException;
}
