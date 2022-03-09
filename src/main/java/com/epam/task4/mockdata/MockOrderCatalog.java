package com.epam.task4.mockdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MockOrderCatalog {
    private static MockOrderCatalog instance;

    public static MockOrderCatalog getInstance() {
        if (instance == null) {
            instance = new MockOrderCatalog();
        }
        return instance;
    }

    private final Map<LocalDateTime, Map<Long, Integer>> orderCatalog  = new TreeMap<>();

    private MockOrderCatalog() {
        initOrderCatalog();
    }

    public Map<LocalDateTime, Map<Long, Integer>> getOrderCatalog() {
        return orderCatalog;
    }

    private void initOrderCatalog() {
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 14, 50, 0),
                Map.of(1L, 2, 2L, 1));
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 17, 20, 0),
                Map.of(4L, 1));
        orderCatalog.put(LocalDateTime.of(2022, 2, 22, 9, 15, 0),
                Map.of(3L, 2));
        orderCatalog.put(LocalDateTime.of(2022, 2, 23, 19, 30, 0),
                Map.of(2L, 1, 3L, 1));
    }

    public void createOrder(LinkedHashMap<Long, Integer> container) {
        orderCatalog.put(LocalDateTime.now(), container);
    }

    public void printOrderList() {
        orderCatalog.entrySet().forEach(getConsumeOrder());
    }

    public static Consumer<Map.Entry<LocalDateTime, Map<Long, Integer>>> getConsumeOrder() {
        return  entry -> {
                System.out.print("(Date: " + entry.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ") { ");
                entry.getValue().forEach((id, amount) ->
                        System.out.print(amount + "x - '" + MockProductCatalog.getInstance()
                                .getProductList().stream().filter(c -> c.getId().equals(id))
                                .collect(Collectors.toList()).get(0).getFrontTitle() + "' "));
                System.out.println("}");
        };
    }
}
