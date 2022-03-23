package com.epam.task4.mockdata;

import com.epam.task1.entity.Commodity;

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
    private final List<Commodity> productCatalog;

    private static MockProductCatalog instance;

    public static MockProductCatalog getInstance() {
        if (instance == null) {
            instance = new MockProductCatalog();
        }
        return instance;
    }

    private MockProductCatalog() {
        productCatalog = new ArrayList<>();
        InitMockResources.initProductCatalog(productCatalog);
    }

    public List<Commodity> getProductCatalog() {
        return productCatalog;
    }
}
