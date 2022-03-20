package com.epam.task4.dal.impl.mock;

import com.epam.task1.entity.Commodity;
import com.epam.task4.dal.ProductRepository;
import com.epam.task4.mockdata.MockProductCatalog;

import java.util.List;

public class ProductRepoMockImpl implements ProductRepository {
    private MockProductCatalog productCatalog = MockProductCatalog.getInstance();

    @Override
    public Commodity getById(Long id) {
        return productCatalog.getProductCatalog().stream()
                .filter(commodity -> commodity.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public int update(Commodity product) {
        try {
            productCatalog.getProductCatalog().set( // update (set) old product
                    productCatalog.getProductCatalog().indexOf( // get index old product
                            productCatalog.getProductCatalog().stream() // get old product by id
                                    .filter(commodity -> commodity.getId().equals(product.getId()))
                                    .findFirst().orElseThrow()), product);
        } catch (Exception exception) { // NoSuchMethodException
            return 0;
        }
        return 1;
    }

    @Override
    public List<Commodity> getAll() {
        return productCatalog.getProductCatalog();
    }
}
