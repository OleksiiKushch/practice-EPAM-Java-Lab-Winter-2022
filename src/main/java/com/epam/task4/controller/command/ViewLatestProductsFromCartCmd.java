package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
public class ViewLatestProductsFromCartCmd implements Command {
    public static final int QUANTITY_LAST_PRODUCTS = 5;

    public static final String FULL_KEY = "--latest-products";
    public static final String SHORT_KEY = "-lp";

    private static final String DESCRIPTION = "View information about the last " + QUANTITY_LAST_PRODUCTS
            + " product that were added to the cart in all shopping sessions";

    private final CartService cartService;

    public ViewLatestProductsFromCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        List<Map.Entry<Commodity, LocalDateTime>> cartHistory = cartService.getHistory();
        if (cartHistory.isEmpty()) {
            System.out.println(ShopLiterals.MSG_CART_HISTORY_IS_EMPTY);
        } else {
            long skip = cartHistory.size() - QUANTITY_LAST_PRODUCTS;
            skip = skip > 0 ? skip : 0;
            cartService.getHistory().stream().skip(skip)
                    .forEach(entry -> System.out.printf(ShopLiterals.OUTPUT_FORMAT_LATEST_PRODUCTS,
                            entry.getKey().toStringWithoutPriceAndAmount(),
                            entry.getValue().format(DateTimeFormatter.ofPattern(ShopLiterals.DATE_FORMAT))));
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
