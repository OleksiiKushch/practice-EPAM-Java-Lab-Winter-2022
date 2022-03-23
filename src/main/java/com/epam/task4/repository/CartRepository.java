package com.epam.task4.repository;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
public interface CartRepository {
    Map<Long, Integer> getAll();
    int insert(Long id, Integer amount);
    Map<Long, LocalDateTime> getHistory();
    int insertInHistory(Long id, LocalDateTime dateTime);
}
