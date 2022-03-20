package com.epam.task4.mockdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class wrapper is used to store the product id ({@link com.epam.task1.entity.Commodity})
 * as container key and appropriate amount (number of products) as container value.
 *
 * @author Oleksii Kushch
 */
public class MockCart {
    /**
     * An associative container that stores the product id as a key and its amount as a value.
     */
    private final Map<Long, Integer> container = new LinkedHashMap<>();

    private static MockCart instance;

    public static MockCart getInstance() {
        if (instance == null) {
            instance = new MockCart();
        }
        return instance;
    }

    private MockCart() {
        // hide
    }

    public Map<Long, Integer> getContainer() {
        return container;
    }
}
