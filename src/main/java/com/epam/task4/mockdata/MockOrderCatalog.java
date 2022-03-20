package com.epam.task4.mockdata;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private final Map<LocalDateTime, List<Commodity>> orderCatalog  = new TreeMap<>();

    private static MockOrderCatalog instance;

    public static MockOrderCatalog getInstance() {
        if (instance == null) {
            instance = new MockOrderCatalog();
        }
        return instance;
    }

    private MockOrderCatalog() {
        initOrderCatalog();
    }

    private void initOrderCatalog() {
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 14, 50, 0),
                new ArrayList<>(Arrays.asList(
                        new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"), 2,
                                "Verity", "Colleen Hoover", "English", 239),
                        new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 1,
                                "Abandoned in Death", "J.D. Robb", "English", 615)
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 17, 20, 0),
                new ArrayList<>(Collections.singletonList(
                        new EReader(4L, "E-Reader12 (GDr-512)", new BigDecimal("28.98"), 1,
                                "GDr-512", 7.2F, 8, 320)
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 22, 9, 15, 0),
                new ArrayList<>(Collections.singletonList(
                        new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"),2,
                                "Ugly Love", "Colleen Hoover", "English", 415,
                                12863, 612, "Jim Dale")
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 23, 19, 30, 0),
                new ArrayList<>(Arrays.asList(
                        new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 1,
                                "Abandoned in Death", "J.D. Robb", "English", 615),
                        new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"), 1,
                                "Ugly Love", "Colleen Hoover", "English", 415,
                                12863, 612, "Jim Dale")
                )));
    }

    public Map<LocalDateTime, List<Commodity>> getOrderCatalog() {
        return orderCatalog;
    }
}
