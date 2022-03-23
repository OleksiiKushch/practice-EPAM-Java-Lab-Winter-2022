package com.epam.task4.repository;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public interface EntityRepository<T> {
    List<T> getAll();
}
