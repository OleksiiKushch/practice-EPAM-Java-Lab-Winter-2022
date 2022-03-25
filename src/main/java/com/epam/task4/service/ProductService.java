package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public interface ProductService extends Service {
    List<Commodity> getAllProducts();
}
