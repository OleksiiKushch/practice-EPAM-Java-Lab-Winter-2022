package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
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
public class ViewCartHistoryCmd implements Command {
    public static final int QUANTITY_LAST_PRODUCTS = 5;

    public static final String FULL_KEY = "--cart-history";
    public static final String SHORT_KEY = "-ch";

    private static final String DESCRIPTION = String.format("Display information about the last %d product that were added to the cart in all shopping sessions (cart history)",
            QUANTITY_LAST_PRODUCTS);

    private final CartService cartService;

    public ViewCartHistoryCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        List<Map.Entry<Commodity, LocalDateTime>> cartHistory = cartService.getHistory();
        if (cartHistory.isEmpty()) {
            MainApp.printAlert(ShopLiterals.MSG_CART_HISTORY_IS_EMPTY);
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
