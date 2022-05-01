package com.epam.task6.create_product;

import com.epam.task4.MainApp;
import com.epam.task7.constants.MessageKey;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class that contain (hold) all product creating strategies which allow an application {@link com.epam.task4.MainApp}.
 * An associative container ({@link #container}) that contains all product creating strategies, takes a name strategy (mode)
 * product creating in string format as a key and a class object {@link CreateProduct} as a value.
 *
 * @author Oleksii Kushch
 */
public class ProductCreatingContainer {
    private final Map<String, CreateProduct> container;

    public ProductCreatingContainer() {
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

    /**
     * print to console existing product types for which there is a strategy for creating
     */
    public void viewExistingEntities() {
        MainApp.printMessage(MessageKey.MSG_KEY_EXISTING_PRODUCT_TYPES);
        container.keySet().forEach(System.out::println);
    }
}
