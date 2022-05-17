package com.epam.task8.util.searcher_prime_number.calculate_part_strategy;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategies for separation into equal parts (intervals)
 * (strictly sequential, e.g. [..., 1, 2, 3, 4, ...] and so on (arithmetic sequence where coefficient 'd' = 1))
 * of the search for the each thread.
 *
 * @author Oleksii Kushch
 */
public class UniformSeparation implements CalculatePartStrategy {
    @Override
    public List<List<Long>> calculateParts(long from, long to, int numberOfThreads) {
        List<Long> stepsSize = calculateStepsSize(from, to, numberOfThreads);
        return fillPartsBySteps(stepsSize, to);
    }

    private List<Long> calculateStepsSize(long from, long to, int numberOfThreads) {
        long rangeLength = to - from;
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            if (i == numberOfThreads - 1) { // last iteration (thread)
                // collected the remainder of the odd division from method "calculateStepSize"
                result.add(rangeLength - result.stream().reduce(NumberUtils.LONG_ZERO, Long::sum) + 1); // ' + 1' because 'to' is inclusive
            } else {
                result.add(calculateStepSize(rangeLength, numberOfThreads));
            }
        }
        return result;
    }

    private long calculateStepSize(long rangeLength, int numberOfThreads) {
        return rangeLength / numberOfThreads;
    }

    /**
     * Noted: reverse order filling parts (to -> from) because the potentially longest part
     * should be the first for a small optimization
     */
    private List<List<Long>> fillPartsBySteps(List<Long> stepsSize, long to) {
        List<List<Long>> result = new ArrayList<>();
        long cursor = to;
        for (long stepSize : stepsSize) {
            List<Long> part = new ArrayList<>();
            fillPart(part, cursor - stepSize + 1, cursor);
            result.add(part);
            cursor -= stepSize;
        }
        return result;
    }

    /**
     * Fill part (interval from to some number) according to arithmetic sequence where coefficient 'd' = 1, e.g. 1, 2, 3, 4, ... and so on.
     */
    private void fillPart(List<Long> part, long from, long to) {
        for (long i = from; i <= to; i++) {
            part.add(i);
        }
    }
}
