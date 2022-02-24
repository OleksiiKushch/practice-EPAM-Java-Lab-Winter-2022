package com.epam.task4.util;

import com.epam.task4.mockdata.MockOrderCatalog;
import com.epam.task4.mockdata.MockProductCatalog;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Cart {
    private static Cart instance;

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    private final LinkedHashMap<Long, Integer> container = new LinkedHashMap<>();
    private final LinkedHashMap<Long, Object> lastProducts = new LinkedHashMap<>(16, 0.75f,true);

    private Cart() {

    }

    public LinkedHashMap<Long, Integer> getContainer() {
        return container;
    }

    public LinkedHashMap<Long, Object> getLastProducts() {
        return lastProducts;
    }

    public void put(Long id, Integer amount) {
        if (container.containsKey(id)) {
            container.put(id, container.get(id) + amount);
        } else {
            container.put(id, amount);
        }
        lastProducts.put(id, null);
    }

    public BigDecimal getSum() {
        return container.entrySet().stream().map((entry) -> MockProductCatalog.getInstance()
                .getProductList().stream().filter(c -> c.getId().equals(entry.getKey()))
                .collect(Collectors.toList()).get(0).getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(new BigDecimal("0.0"), BigDecimal::add);
    }

    public void checkout() {
        MockOrderCatalog mockOrderCatalog = MockOrderCatalog.getInstance();
        if (container.isEmpty()) {
            System.out.println("Cart is empty, please select and add the product you want to cart");
        } else {
            mockOrderCatalog.createOrder(new LinkedHashMap<>(container));
            System.out.println("Sum: " + getSum());
            System.out.println("Checkout was successful!");
            container.clear();
        }
    }
}
