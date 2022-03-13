package com.epam.task4.mockdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Class mock stub (imitation "data store") for store "order" (some abstract entity). {@link TreeMap} is used
 * to store orders ({@link #orderCatalog}), the date of creation of the order is used <b>as the key</b>, and the associative container
 * (similar to the container for the {@link com.epam.task4.util.Cart}) is used <b>as the value</b>
 * (the product id {@link com.epam.task1.entity.Commodity} is stored as the key, and its (product)
 * amount as the value).
 *
 * @author Oleksii Kushch
 */
public class MockOrderCatalog {
    private static MockOrderCatalog instance;

    public static MockOrderCatalog getInstance() {
        if (instance == null) {
            instance = new MockOrderCatalog();
        }
        return instance;
    }

    /**
     * A <b>container</b> that stores the creation date of the order <b>as a key</b>, and the associative container
     * that stores the product id as a key and its amount <b>as a value</b>.
     */
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

    /**
     * Create "order" (some abstract entity) based local date-time {@code LocalDateTime.now()} and @param {@code container}
     * and add (put) its to order catalog ({@link #orderCatalog}).
     *
     * @param container the associative container (similar to the container for the {@link com.epam.task4.util.Cart})
     * (the product id {@link com.epam.task1.entity.Commodity} is stored as the key, and its (product) amount as the value).
     */
    public void createOrder(LinkedHashMap<Long, Integer> container) {
        orderCatalog.put(LocalDateTime.now(), container);
    }

    /**
     * Print to console all orders from order catalog {@link #orderCatalog} to specific format.
     */
    public void printOrderList() {
        orderCatalog.entrySet().forEach(getConsumeOrder());
    }

    /**
     * @return consumer (functional interface) which implements a specific output format
     */
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
