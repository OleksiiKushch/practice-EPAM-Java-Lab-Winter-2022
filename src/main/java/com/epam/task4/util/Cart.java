package com.epam.task4.util;

import com.epam.task4.mockdata.MockOrderCatalog;
import com.epam.task4.mockdata.MockProductCatalog;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

/**
 * The class wrapper is used to store the product id ({@link com.epam.task1.entity.Commodity})
 * as container key and appropriate amount (number of products) as container value.
 * <p>
 * In addition to the usual container ({@link #container}), there is a special container ({@link #lastProducts}),
 * which stores the identifiers of the last products that were in the cart for all shopping sessions.
 * See {@link com.epam.task4.controller.command.ViewLatestProductsFromCartCommand}.
 *
 * @author Oleksii Kushch
 */
public class Cart {
    private static Cart instance;

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    /**
     * An associative container that stores the product id as a key and its amount as a value.
     */
    private final LinkedHashMap<Long, Integer> container = new LinkedHashMap<>();

    /**
     * A special container building a kind of stack-like structure, where the products (their id) are lined
     * up in the order of the "last added".
     * <p>
     * Noted: For this special logic, a third optional parameter ({@code accessOrder})
     * has been added to the constructor ({@link LinkedHashMap#LinkedHashMap(int, float, boolean)}).
     */
    private final LinkedHashMap<Long, Object> lastProducts = new LinkedHashMap<>(16, 0.75f,true);

    private Cart() {
        // hide
    }

    public LinkedHashMap<Long, Integer> getContainer() {
        return container;
    }

    public LinkedHashMap<Long, Object> getLastProducts() {
        return lastProducts;
    }

    /**
     * Add (put) products id to container ({@link #container}) and groups its summing their (products) amount
     * accordingly. Also add (put) product id to special container ({@link #lastProducts}), building a kind
     * of stack-like structure, where the products (their id) are lined up in the order of the "last added".
     *
     * @param id the product id ({@link com.epam.task1.entity.Commodity})
     * @param amount amount (number of products)
     */
    public void put(Long id, Integer amount) {
        if (container.containsKey(id)) {
            container.put(id, container.get(id) + amount);
        } else {
            container.put(id, amount);
        }
        lastProducts.put(id, null);
    }

    /**
     * The sum of the multiply of the content (the price of the product and its quantity)
     * of all pairs of the container ({@link #container}).
     * <p>
     * The product price is obtained by finding the corresponding product by its id (key in the cart
     * container ({@link #container})) from the product catalog ({@link MockProductCatalog}).
     * @return total sum of the cart content
     */
    public BigDecimal getSum() {
        return container.entrySet().stream().map((entry) -> MockProductCatalog.getInstance()
                .getProductList().stream().filter(c -> c.getId().equals(entry.getKey()))
                .collect(Collectors.toList()).get(0).getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(new BigDecimal("0.0"), BigDecimal::add);
    }

    /**
     * Imitation the logic of checkout an order (cart content). The following steps are carried out in the process:
     * <ul>
     *     <li> Create new order in order catalog ({@link MockOrderCatalog})
     *     <li> output to console total sum of order (total sum the content of cart) and reports the success of the operation
     *     <li> clear the content of cart
     * </ul>
     * <p>
     * Noted: operation cannot be execute if cart is empty
     */
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
