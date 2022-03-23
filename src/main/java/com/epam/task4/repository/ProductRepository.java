package com.epam.task4.repository;

import com.epam.task1.entity.Commodity;

/**
 * @author Oleksii Kushch
 */
public interface ProductRepository extends EntityRepository<Commodity> {
    Commodity getById(Long id);
}
