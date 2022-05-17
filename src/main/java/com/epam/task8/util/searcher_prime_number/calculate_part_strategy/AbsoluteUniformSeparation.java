package com.epam.task8.util.searcher_prime_number.calculate_part_strategy;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategies for separation into parts (intervals) according to arithmetic sequence
 * where coefficient 'd' = {@link #STEP} * {@code numberOfThreads} - 1
 * for each thread. e.g. [..., 3, 4, 11, 12, ...] for one of threads where them of 4
 *
 * Noted: Good variant for power of two (2, 4, 8, 16 ...) threads, because the features of the distribution of prime numbers
 *
 * @author Oleksii Kushch
 */
public class AbsoluteUniformSeparation implements CalculatePartStrategy {
    public static long STEP = 2;

    @Override
    public List<List<Long>> calculateParts(long from, long to, int numberOfThreads) {
        long rangeLength = to - from;
        List<List<Long>> result = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            List<Long> part = new ArrayList<>();
            long cursor = from + i * STEP;
            for (long j = 0; j < rangeLength / numberOfThreads + 1; j++) {
                if (cursor > to) {
                    break;
                }
                part.add(cursor);
                if (j % STEP == NumberUtils.LONG_ZERO) {
                    cursor += STEP * numberOfThreads - 1;
                } else {
                    cursor++;
                }
            }
            result.add(part);
        }
        return result;
    }
}
