package com.epam.task8.util.searcher_prime_number.collecting_strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that implements the strategy of thread-safe saving numbers to a general
 * (common) collection as soon as they are found.
 *
 * @author Oleksii Kushch
 */
public class GeneralCollecting implements CollectingStrategy {
    private final List<Long> result;

    public GeneralCollecting() {
        result = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void save(Long number, Thread thread) {
        result.add(number);
    }

    @Override
    public List<Long> getResult() {
        return result;
    }
}
