package com.epam.task4.mockdata;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class mock stub (imitation "data store") for store "product" (commodity) (some abstract entity ({@link Commodity})).
 * {@link ArrayList} is used to store products ({@link #productCatalog}), for data elements used are objects concrete
 * implementation classes of the abstract class {@link Commodity}.
 *
 * @author Oleksii Kushch
 */
public class MockProductCatalog {
    /**
     * A container that stores objects concrete implementation classes of the abstract class {@link Commodity}.
     */
    private final List<Commodity> productCatalog = new ArrayList<>();

    private static MockProductCatalog instance;

    public static MockProductCatalog getInstance() {
        if (instance == null) {
            instance = new MockProductCatalog();
        }
        return instance;
    }

    private MockProductCatalog() {
        initProductCatalog();
    }

    private void initProductCatalog() {
        productCatalog.add(new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"), 100,
                        "Verity", "Colleen Hoover", "English", 239));
        productCatalog.add(new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 30,
                "Abandoned in Death", "J.D. Robb", "English", 615));
        productCatalog.add(new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"), 45,
                "Ugly Love", "Colleen Hoover", "English", 415,
                12863, 612, "Jim Dale"));
        productCatalog.add(new EReader(4L, "E-Reader12 (GDr-512)", new BigDecimal("28.98"), 70,
                "GDr-512", 7.2F, 8, 320));
        productCatalog.add(new EReader(5L, "E-Reader13a+ (GDr-513a)", new BigDecimal("36.68"), 30,
                "GDr-513a", 8.0F, 8, 360));
        productCatalog.add(new Book(6L, "Family Money (Chad Z.)", new BigDecimal("4.99"), 10,
                "Family Money", "Chad Zunker", "English", 239));
    }

    public List<Commodity> getProductCatalog() {
        return productCatalog;
    }
}
