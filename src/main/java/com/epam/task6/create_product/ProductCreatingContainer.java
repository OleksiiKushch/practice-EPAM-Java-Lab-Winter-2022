package com.epam.task6.create_product;

import com.epam.task4.constants.ShopLiterals;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductCreatingContainer {
    private final Map<String, CreateProduct> container;

    public ProductCreatingContainer() {
        container = new LinkedHashMap<>();
    }

    public Map<String, CreateProduct> getCommands() {
        return container;
    }

    public boolean isContainEntity(String entityKey) {
        return container.containsKey(entityKey);
    }

    public CreateProduct getEntityByKey(String entityKey) {
        return container.get(entityKey);
    }

    public void viewExistingEntities() {
        System.out.println(ShopLiterals.MSG_EXISTING_PRODUCT_TYPES);
        container.keySet().forEach(System.out::println);
    }
}
