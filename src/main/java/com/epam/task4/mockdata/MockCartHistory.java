package com.epam.task4.mockdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class wrapper is used to store the product id ({@link com.epam.task1.entity.Commodity})
 * as container key. There is a special container (last products) ({@link #container}),
 * which stores the identifiers of the last products that were in the cart for all shopping sessions.
 * See {@link com.epam.task4.controller.command.ViewLatestProductsFromCartCMD}.
 *
 * @author Oleksii Kushch
 */
public class MockCartHistory {
    /**
     * A special container building a kind of stack-like structure, where the products (their id) are lined
     * up in the order of the "last added".
     * <p>
     * Noted: For this special logic, a third optional parameter ({@code accessOrder})
     * has been added to the constructor ({@link LinkedHashMap#LinkedHashMap(int, float, boolean)}).
     */
    private final LinkedHashMap<Long, Object> container = new LinkedHashMap<>(16, 0.75f,true);

    private static MockCartHistory instance;

    public static MockCartHistory getInstance() {
        if (instance == null) {
            instance = new MockCartHistory();
        }
        return instance;
    }

    private MockCartHistory() {
        // hide
    }

    public Map<Long, Object> getContainer() {
        return container;
    }
}
