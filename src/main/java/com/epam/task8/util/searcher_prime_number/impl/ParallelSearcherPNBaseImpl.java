package com.epam.task8.util.searcher_prime_number.impl;

import com.epam.task8.util.searcher_prime_number.ParallelSearcherPrimeNumbers;
import com.epam.task8.util.searcher_prime_number.calculate_part_strategy.CalculatePartStrategy;
import com.epam.task8.util.searcher_prime_number.collecting_strategy.CollectingStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelSearcherPNBaseImpl implements ParallelSearcherPrimeNumbers {
    private static final Logger log = LogManager.getLogger(ParallelSearcherPNBaseImpl.class);

    private final long from;
    private final long to;
    private final int numberOfThreads;

    private final CalculatePartStrategy calculatePartStrategy;
    private final CollectingStrategy collectingStrategy;

    public ParallelSearcherPNBaseImpl(long from, long to, int numberOfThreads,
                                      CalculatePartStrategy calculatePartStrategy,
                                      CollectingStrategy collectingStrategy) {
        this.from = from;
        this.to = to;
        this.numberOfThreads = numberOfThreads;

        this.calculatePartStrategy = calculatePartStrategy;
        this.collectingStrategy = collectingStrategy;
    }

    @Override
    public List<Long> findAllInRange() {
        List<List<Long>> parts = calculatePartStrategy.calculateParts(from, to, numberOfThreads);

        log.debug("Parts size: " + parts.stream().map(List::size).collect(Collectors.toList()));
        for (List<Long> part : parts) {
            log.debug(part);
        }

        List<Thread> threads = initThreads(parts);
        threads.forEach(Thread::start);
        joinThreads(threads);
        return collectingStrategy.getResult();
    }

    private class FindAllInRangeThread extends Thread {
        private final List<Long> part;

        public FindAllInRangeThread(List<Long> part) {
            this.part = part;
        }

        @Override
        public void run() {
            log.debug(String.format("Thread (%d) START; Part: [%d; %d]; Size: %d", this.getId(),
                    part.size() == 0 ? 0 : part.get(0), part.size() == 0 ? 0 : part.get(part.size() - 1), part.size()));
            long beforeTime = System.currentTimeMillis();

            for (long number : part) {
                if (isPrimeNumber(number)) {
                    collectingStrategy.save(number, this);
                }
            }

            log.debug(String.format("Thread (%d) DONE; Time: %d", this.getId(), System.currentTimeMillis() - beforeTime));
        }
    }

    private List<Thread> initThreads(List<List<Long>> parts) {
        List<Thread> threads = new ArrayList<>();
        for (List<Long> part : parts) {
            threads.add(new FindAllInRangeThread(part));
        }
        return threads;
    }

    private void joinThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}