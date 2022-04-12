package com.epam.task6.create_product;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task6.create_product.mirror_wrapper_entity.InitCommodity;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityContainer {
    private final Map<String, InitCommodity> container;

    public EntityContainer() {
        container = new LinkedHashMap<>();
    }

    public Map<String, InitCommodity> getCommands() {
        return container;
    }

    public boolean isContainEntity(String entityKey) {
        return container.containsKey(entityKey);
    }

    public InitCommodity getEntityByKey(String entityKey) {
        return container.get(entityKey);
    }

    public void viewExistingEntities() {
        System.out.println(ShopLiterals.MSG_EXISTING_PRODUCT_TYPES);
        container.keySet().forEach(System.out::println);
    }
}
