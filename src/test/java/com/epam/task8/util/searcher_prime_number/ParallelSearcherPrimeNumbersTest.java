package com.epam.task8.util.searcher_prime_number;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelSearcherPrimeNumbersTest {
    private static class ParallelSearcherPNTestImpl implements ParallelSearcherPrimeNumbers {
        @Override
        public List<Long> findAllInRange() {
            throw new RuntimeException("Not implemented for tests");
        }
    }

    @Test
    void isPrimeNumber() {
        ParallelSearcherPrimeNumbers testSearcher = new ParallelSearcherPNTestImpl();
        assertFalse(testSearcher.isPrimeNumber(-3));
        assertFalse(testSearcher.isPrimeNumber(0));
        assertFalse(testSearcher.isPrimeNumber(1));
        assertTrue(testSearcher.isPrimeNumber(2));
        assertTrue(testSearcher.isPrimeNumber(3));
        assertTrue(testSearcher.isPrimeNumber(19));
    }
}