package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.dal.ProductRepository;
import com.epam.task4.dal.RepositoryFactory;
import com.epam.task4.service.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl() {

    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void initMockRepository() {
        RepositoryFactory repositoryFactory = null;
        try {
            repositoryFactory = RepositoryFactory.getInstance();
        } catch (NoSuchMethodException | IllegalAccessException |
                InvocationTargetException | InstantiationException exception) {
            exception.printStackTrace();
        }
        Objects.requireNonNull(repositoryFactory);
        productRepository = repositoryFactory.getProductRepository();
    }

    @Override
    public Commodity getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Commodity> getAllProducts() {
        return productRepository.getAll();
    }

    /**
     * @param id product id ({@link Commodity})
     * @return true if products with this id is exists to product catalog ({@link com.epam.task4.mockdata.MockProductCatalog}),
     * false if is not exists
     */
    @Override
    public boolean isContainProduct(Long id) {
        return productRepository.getAll().stream()
                .anyMatch(commodity -> commodity.getId().equals(id));
    }
}
