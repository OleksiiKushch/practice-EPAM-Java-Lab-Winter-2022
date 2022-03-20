package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.math.BigDecimal;
import java.util.List;

public interface CartService extends EntityService {
    Commodity getProductById(Long id);
    List<Commodity> getAllProducts();
    void interactivePut();
    void put(Long id, Integer amount);
    void checkout();
    BigDecimal getSum();
}
