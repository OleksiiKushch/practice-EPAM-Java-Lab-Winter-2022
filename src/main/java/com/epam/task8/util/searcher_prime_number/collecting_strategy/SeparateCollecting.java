package com.epam.task8.util.searcher_prime_number.collecting_strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that implements the strategy of saving found numbers into separate collections
 * of each thread, and then add their contents to the general collection using the method
 * {@link #collectResults()}.
 *
 * @author Oleksii Kushch
 */
public class SeparateCollecting implements CollectingStrategy {
    private final Map<Thread, List<Long>> threadAndResult;

    public SeparateCollecting() {
        threadAndResult = new HashMap<>();
    }

    @Override
    public void save(Long number, Thread thread) {
        if (threadAndResult.containsKey(thread)) {
            threadAndResult.get(thread).add(number);
        } else {
            List<Long> container = new ArrayList<>();
            container.add(number);
            threadAndResult.put(thread, container);
        }
    }

    @Override
    public List<Long> getResult() {
        return collectResults();
    }

    private List<Long> collectResults() {
        List<Long> result = new ArrayList<>();
        for (Map.Entry<Thread, List<Long>> thread : threadAndResult.entrySet()) {
            result.addAll(thread.getValue());
        }
        return result;
    }
}
