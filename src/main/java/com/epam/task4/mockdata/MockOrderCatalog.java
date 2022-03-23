package com.epam.task4.mockdata;

import com.epam.task1.entity.Commodity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class mock stub (imitation "data store") for store "order" (some abstract entity). {@link TreeMap} is used
 * to store orders ({@link #orderCatalog}), the date of creation of the order is used <b>as the key</b>, and the associative container
 * (similar to the container for the {@link MockCart}) is used <b>as the value</b>
 * (the product id {@link com.epam.task1.entity.Commodity} is stored as the key, and its (product)
 * amount as the value).
 *
 * @author Oleksii Kushch
 */
public class MockOrderCatalog {
    /**
     * A <b>container</b> that stores the creation date of the order <b>as a key</b>, and the associative container
     * that stores the product id as a key and its amount <b>as a value</b>.
     */
    private final Map<LocalDateTime, List<Commodity>> orderCatalog;

    private static MockOrderCatalog instance;

    public static MockOrderCatalog getInstance() {
        if (instance == null) {
            instance = new MockOrderCatalog();
        }
        return instance;
    }

    private MockOrderCatalog() {
        orderCatalog = new TreeMap<>();
        InitMockResources.initOrderCatalog(orderCatalog);
    }

    public Map<LocalDateTime, List<Commodity>> getOrderCatalog() {
        return orderCatalog;
    }
}
