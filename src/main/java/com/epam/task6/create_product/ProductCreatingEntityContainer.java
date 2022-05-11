package com.epam.task6.create_product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class that contain (hold) all product creating strategies which allow an application {@link com.epam.task4.MainApp}.
 * An associative container ({@link #container}) that contains all product creating strategies, takes a name strategy (mode)
 * product creating in string format as a key and a class object {@link CreateProduct} as a value.
 *
 * @author Oleksii Kushch
 */
public class ProductCreatingEntityContainer {
    private final Map<String, CreateProduct> container;
    private final ProductCreatingStrategy productCreatingStrategy;

    public ProductCreatingEntityContainer(ProductCreatingStrategy productCreatingStrategy) {
        this.productCreatingStrategy = productCreatingStrategy;
        container = new LinkedHashMap<>();
    }

    public void put(String entityKey, CreateProduct createProduct) {
        container.put(entityKey, createProduct);
    }

    public boolean isContainEntity(String entityKey) {
        return container.containsKey(entityKey);
    }

    public CreateProduct getEntityByKey(String entityKey) {
        return container.get(entityKey);
    }

    public ProductCreatingStrategy getProductCreatingStrategy() {
        return productCreatingStrategy;
    }

    /**
     * print to console existing product types for which there is a strategy for creating
     */
    public void viewExistingEntities() {
        container.keySet().forEach(System.out::println);
    }
}
