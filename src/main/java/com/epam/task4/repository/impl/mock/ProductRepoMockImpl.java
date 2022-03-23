package com.epam.task4.repository.impl.mock;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.mockdata.MockProductCatalog;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ProductRepoMockImpl implements ProductRepository {
    private final MockProductCatalog productCatalog = MockProductCatalog.getInstance();

    @Override
    public Commodity getById(Long id) {
        return productCatalog.getProductCatalog().stream()
                .filter(commodity -> commodity.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Commodity> getAll() {
        return productCatalog.getProductCatalog();
    }
}
