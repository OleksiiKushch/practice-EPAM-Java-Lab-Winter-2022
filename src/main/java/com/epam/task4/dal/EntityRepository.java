package com.epam.task4.dal;

import java.util.List;

public interface EntityRepository<T> {
    List<T> getAll();
}
