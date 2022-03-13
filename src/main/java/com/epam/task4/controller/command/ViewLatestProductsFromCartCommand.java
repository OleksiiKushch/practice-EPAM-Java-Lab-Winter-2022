package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockProductCatalog;
import com.epam.task4.util.Cart;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Oleksii Kushch
 */
public class ViewLatestProductsFromCartCommand implements Command {
    public static final int QUANTITY_LAST_PRODUCTS = 5;

    @Override
    public void execute() {
        Map<Long, Object> lastProducts = Cart.getInstance().getLastProducts();
        long skip = lastProducts.size() - QUANTITY_LAST_PRODUCTS;
        skip = skip  > 0 ? skip : 0;
        lastProducts.keySet().stream().skip(skip)
                .forEach((id) -> System.out.println(MockProductCatalog.getInstance()
                                .getProductList().stream().filter(c -> c.getId().equals(id))
                                .collect(Collectors.toList()).get(0).getFrontTitle()));
    }

    @Override
    public String getDescription() {
        return "View information about the last " + QUANTITY_LAST_PRODUCTS
                + " product that were added to the cart in all shopping sessions";
    }
}
