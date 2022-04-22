package com.epam.task4.repository.impl.mock;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.model.data_sources.ProductCatalog;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductCatalog productCatalog;

    public ProductRepositoryImpl(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public Commodity getById(Long id) {
        return productCatalog.getProductCatalog().stream()
                .filter(commodity -> commodity.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public int insert(Commodity commodity) {
        productCatalog.getProductCatalog().add(commodity);
        return 1;
    }

    @Override
    public List<Commodity> getAll() {
        return productCatalog.getProductCatalog();
    }
}
