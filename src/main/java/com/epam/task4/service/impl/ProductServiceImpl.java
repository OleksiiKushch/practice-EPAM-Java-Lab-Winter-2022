package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.repository.factory.RepositoryFactory;
import com.epam.task4.service.ProductService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl() {
        // default constructor
    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void initMockRepository() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        productRepository = repositoryFactory.getProductRepository();
    }

    @Override
    public List<Commodity> getAllProducts() {
        return productRepository.getAll();
    }
}
