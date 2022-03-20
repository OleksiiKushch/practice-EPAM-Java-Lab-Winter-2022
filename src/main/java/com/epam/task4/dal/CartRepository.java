package com.epam.task4.dal;

import java.util.Map;

public interface CartRepository {
    Map<Long, Integer> getAll();
    int insert(Long id, Integer amount);
}
