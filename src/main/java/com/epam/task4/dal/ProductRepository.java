package com.epam.task4.dal;

import com.epam.task1.entity.Commodity;

public interface ProductRepository extends EntityRepository<Commodity> {
    Commodity getById(Long id);
    int update(Commodity product);
}
