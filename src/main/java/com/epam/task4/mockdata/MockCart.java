package com.epam.task4.mockdata;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class wrapper is used to store the product id ({@link com.epam.task1.entity.Commodity})
 * as container key and appropriate amount (number of products) as container value.
 * <p>
 * Also there is a special container (last products) ({@link #container}),
 * which stores the identifiers of the last products that were in the cart for all shopping sessions.
 * See {@link com.epam.task4.controller.command.ViewLatestProductsFromCartCmd}.
 *
 * @author Oleksii Kushch
 */
public class MockCart {
    /**
     * An associative container that stores the product id as a key and its amount as a value.
     */
    private final Map<Long, Integer> container;

    /**
     * A special container building a kind of stack-like structure, where the products (their id) are lined
     * up in the order of the "last added".
     * <p>
     * Noted: For this special logic, a third optional parameter ({@code accessOrder})
     * has been added to the constructor ({@link LinkedHashMap#LinkedHashMap(int, float, boolean)}).
     */
    private final LinkedHashMap<Long, LocalDateTime> history;

    private static MockCart instance;

    public static MockCart getInstance() {
        if (instance == null) {
            instance = new MockCart();
        }
        return instance;
    }

    private MockCart() {
        container = new LinkedHashMap<>();
        history = new LinkedHashMap<>(16, 0.75f,true);
    }

    public Map<Long, Integer> getContainer() {
        return container;
    }

    public Map<Long, LocalDateTime> getHistory() {
        return history;
    }
}
