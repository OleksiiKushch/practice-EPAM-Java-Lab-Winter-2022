package com.epam.task8.util.searcher_prime_number.calculate_part_strategy;

import java.util.List;

public interface CalculatePartStrategy {
    List<List<Long>> calculateParts(long from, long to, int numberOfThreads);
}
