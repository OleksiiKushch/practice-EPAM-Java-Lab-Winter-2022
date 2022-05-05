package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;

import java.math.BigDecimal;

/**
 * @author Oleksii Kushch
 */
public class CheckoutCmd implements Command {
    public static final String FULL_KEY = "--checkout";

    private static final String DESCRIPTION = "Buy all items from the cart (checkout)";

    private final CartService cartService;

    public CheckoutCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        BigDecimal sum = cartService.getSum();
        if (cartService.isEmpty()) {
            MainApp.printAlert(ShopLiterals.MSG_ALERT_CART_IS_EMPTY);
        } else {
            cartService.checkout();
            MainApp.printSuccessMessage(ShopLiterals.MSG_CHECKOUT_SUCCESS);
            MainApp.print(ShopLiterals.SUM + ShopLiterals.SPACE + sum);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
