package com.epam.task4.dal;

import java.util.Map;

public interface CartHistoryRepository {
    Map<Long, Object> getAll();
    int insert(Long id, Object ignore);
}
