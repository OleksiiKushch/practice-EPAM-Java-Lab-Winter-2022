package com.epam.task4.model.data_sources;

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
public class ProductCatalog {
    /**
     * A container that stores objects concrete implementation classes of the abstract class {@link Commodity}.
     */
    private final List<Commodity> productCatalog;

    public ProductCatalog() {
        productCatalog = new ArrayList<>();
    }

    public ProductCatalog(List<Commodity> productCatalog) {
        this.productCatalog = productCatalog;
    }

    public List<Commodity> getProductCatalog() {
        return productCatalog;
    }
}
