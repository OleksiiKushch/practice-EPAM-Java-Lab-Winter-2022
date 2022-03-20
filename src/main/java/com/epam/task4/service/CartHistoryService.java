package com.epam.task4.service;

import com.epam.task1.entity.Commodity;

import java.util.List;

public interface CartHistoryService extends EntityService {
    List<Commodity> getAllProducts();
}
