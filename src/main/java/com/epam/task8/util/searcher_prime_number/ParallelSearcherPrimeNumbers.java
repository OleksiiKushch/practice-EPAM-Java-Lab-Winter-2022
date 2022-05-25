package com.epam.task8.util.searcher_prime_number;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public interface ParallelSearcherPrimeNumbers {
    long TWO = 2;

    List<Long> findAllInRange();

    default boolean isPrimeNumber(long number) {
        if (number < TWO) {
            return false;
        }
        for (long i = TWO; i <= number / TWO; i++) {
            if (number % i == NumberUtils.LONG_ZERO) {
                return false;
            }
        }
        return true;
    }
}
