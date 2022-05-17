package com.epam.task8.util.searcher_prime_number.collecting_strategy;

import java.util.List;

public interface CollectingStrategy {
    void save(Long number, Thread thread);
    List<Long> getResult();
}
