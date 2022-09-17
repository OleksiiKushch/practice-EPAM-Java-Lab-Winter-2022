package com.epam.task14.util;

import com.epam.task11.entity.Entity;
import com.epam.task13.entity.product.Product;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Cart extends Entity {
    @Serial
    private static final long serialVersionUID = -2184813221669037215L;
    private Map<Product, Integer> container;

    public Cart() {
        container = new LinkedHashMap<>();
    }

    public Map<Product, Integer> getContainer() {
        return container;
    }

    public void put(Product product, Integer amount) {
        Optional<Map.Entry<Product, Integer>> optionalProductInCart = getContent().stream().filter(entry -> entry.getKey().getId() == product.getId()).findFirst();
        if (optionalProductInCart.isPresent()) {
            Map.Entry<Product, Integer> productInCart = optionalProductInCart.get();
            container.remove(productInCart.getKey());
            container.put(product, productInCart.getValue() + amount);
        } else {
            container.put(product, amount);
        }
    }

    public void remove(Product product) {
        Optional<Map.Entry<Product, Integer>> optionalProductInCart = getContent().stream().filter(entry -> entry.getKey().getId() == product.getId()).findFirst();
        optionalProductInCart.ifPresent(productIntegerEntry -> container.remove(productIntegerEntry.getKey()));
    }

    public BigDecimal getTotalSum() {
        BigDecimal result = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : getContent()) {
            result = result.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        return result;
    }

    public Set<Map.Entry<Product, Integer>> getContent() {
        return container.entrySet();
    }

    public void clean() {
        container.clear();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public void changeAmount(Product product, Integer amount) {
        Optional<Map.Entry<Product, Integer>> optionalProductInCart = getContent().stream().filter(entry -> entry.getKey().getId() == product.getId()).findFirst();
        if (optionalProductInCart.isPresent()) {
            Map.Entry<Product, Integer> productInCart = optionalProductInCart.get();
            container.remove(productInCart.getKey());
            container.put(product, amount);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "container=" + container +
                '}';
    }
}
