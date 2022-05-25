package com.epam.task8.util.searcher_prime_number.impl;

import com.epam.task8.util.searcher_prime_number.ParallelSearcherPrimeNumbers;
import com.epam.task8.util.searcher_prime_number.calculate_part_strategy.CalculatePartStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Implementation parallel (multi-thread) searcher prime number with using interface {@link ExecutorService}.
 *
 * @author Oleksii Kushch
 */
public class ParallelSearcherPNExecutorsImpl implements ParallelSearcherPrimeNumbers {
    private final long from;
    private final long to;
    private final int numberOfThreads;

    private final CalculatePartStrategy calculatePartStrategy;

    public ParallelSearcherPNExecutorsImpl(long from, long to, int numberOfThreads,
                                           CalculatePartStrategy calculatePartStrategy) {
        this.from = from;
        this.to = to;
        this.numberOfThreads = numberOfThreads;

        this.calculatePartStrategy = calculatePartStrategy;
    }

    @Override
    public List<Long> findAllInRange() {
        List<List<Long>> parts = calculatePartStrategy.calculateParts(from, to, numberOfThreads);
        List<Callable<List<Long>>> tasks = initTasks(parts);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<List<Long>>> results = new ArrayList<>();
        try {
            results = executorService.invokeAll(tasks);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
        return collectResult(results);
    }

    private class SearcherPrimeNumbers implements Callable<List<Long>> {
        private final List<Long> part;

        public SearcherPrimeNumbers(List<Long> part) {
            this.part = part;
        }

        @Override
        public List<Long> call() {
            List<Long> result = new ArrayList<>();
            for (long number : part) {
                if (isPrimeNumber(number)) {
                    result.add(number);
                }
            }
            return result;
        }
    }

    private List<Callable<List<Long>>> initTasks(List<List<Long>> parts) {
        List<Callable<List<Long>>> tasks = new ArrayList<>();
        for (List<Long> part : parts) {
            tasks.add(new SearcherPrimeNumbers(part));
        }
        return tasks;
    }

    private List<Long> collectResult(List<Future<List<Long>>> results) {
        List<Long> result = new ArrayList<>();
        for (Future<List<Long>> sublist : results) {
            try {
                result.addAll(sublist.get());
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }
}
