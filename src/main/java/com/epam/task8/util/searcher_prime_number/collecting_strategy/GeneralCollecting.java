package com.epam.task8.util.searcher_prime_number.collecting_strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that implements the strategy of thread-safe saving numbers to a general
 * (common) collection as soon as they are found.
 *
 * @author Oleksii Kushch
 */
public class GeneralCollecting implements CollectingStrategy {
    private final ReentrantLock lock;
    private final List<Long> result;

    public GeneralCollecting() {
        result = new ArrayList<>();
        lock = new ReentrantLock();
    }

    @Override
    public void save(Long number, Thread thread) {
        lock.lock();
        try {
            result.add(number);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Long> getResult() {
        return result;
    }
}
