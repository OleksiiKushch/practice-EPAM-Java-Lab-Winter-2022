package com.epam.task13.service;

import com.epam.task13.entity.product.Product;
import com.epam.task13.util.PagePaginationData;
import com.epam.task13.util.ProductFilterFormBean;
import com.epam.task13.util.SortingData;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getProductById(int id);
    List<Product> findProducts(ProductFilterFormBean productFilterFormBean, SortingData sortingData, PagePaginationData pagePaginationData);
    int getCountOfProductsWithFiltering(ProductFilterFormBean productFilterFormBean);
    BigDecimal getMinProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean);
    BigDecimal getMaxProductPriceWithFiltering(ProductFilterFormBean productFilterFormBean);
}
