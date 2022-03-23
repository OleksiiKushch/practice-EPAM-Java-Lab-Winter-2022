package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.model.entity.Order;
import com.epam.task4.service.CartService;

import java.time.format.DateTimeFormatter;

/**
 * @author Oleksii Kushch
 */
public class ViewLatestProductsFromCartCmd implements Command {
    public static final int QUANTITY_LAST_PRODUCTS = 5;

    public static final String FULL_KEY = "--latest-products";
    public static final String SHORT_KEY = "-lp";

    public static final String DESCRIPTION = "View information about the last " + QUANTITY_LAST_PRODUCTS
            + " product that were added to the cart in all shopping sessions";

    private static final String OUTPUT_FORMAT = "%s [date last put to cart: %s];%n";

    private final CartService cartService;

    public ViewLatestProductsFromCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        cartService.initRepository();
        long skip = cartService.getHistory().size() - QUANTITY_LAST_PRODUCTS;
        skip = skip  > 0 ? skip : 0;
        cartService.getHistory().stream().skip(skip)
                .forEach(entry -> System.out.printf(OUTPUT_FORMAT, entry.getKey().toStringWithoutPriceAndAmount(),
                                entry.getValue().format(DateTimeFormatter.ofPattern(Order.DATE_FORMAT))));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
