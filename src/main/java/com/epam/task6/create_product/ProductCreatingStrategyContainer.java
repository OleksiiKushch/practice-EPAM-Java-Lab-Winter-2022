package com.epam.task6.create_product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductCreatingStrategyContainer {
    private final Map<String, ProductCreatingEntityContainer> container;

    public ProductCreatingStrategyContainer() {
        container = new LinkedHashMap<>();
    }

    public void put(String strategyKey, ProductCreatingEntityContainer productCreatingEntityContainer) {
        container.put(strategyKey, productCreatingEntityContainer);
    }

    public boolean isContainStrategy(String strategyKey) {
        return container.containsKey(strategyKey);
    }

    public ProductCreatingEntityContainer getEntityContainerByKey(String strategyKey) {
        return container.get(strategyKey);
    }

    public void viewExistingProductCreatingStrategies() {
        container.values().stream().distinct()
                .map(container -> container.getProductCreatingStrategy().getFullView())
                .collect(Collectors.toList())
                .stream().sorted()
                .forEach(System.out::println);
    }
}
