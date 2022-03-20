package com.epam.task4.service.impl;

import com.epam.task1.entity.Commodity;
import com.epam.task4.dal.CartHistoryRepository;
import com.epam.task4.dal.ProductRepository;
import com.epam.task4.dal.RepositoryFactory;
import com.epam.task4.service.CartHistoryService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartHistoryServiceImpl implements CartHistoryService {
    private CartHistoryRepository cartHistoryRepository;
    private ProductRepository productRepository;

    public CartHistoryServiceImpl() {

    }

    public CartHistoryServiceImpl(CartHistoryRepository cartHistoryRepository,
                                  ProductRepository productRepository) {
        this.cartHistoryRepository = cartHistoryRepository;
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
        cartHistoryRepository = repositoryFactory.getCartHistoryRepository();
    }

    @Override
    public List<Commodity> getAllProducts() {
        return cartHistoryRepository.getAll().keySet().stream()
                .map(id -> productRepository.getById(id))
                .collect(Collectors.toList());
    }
}
