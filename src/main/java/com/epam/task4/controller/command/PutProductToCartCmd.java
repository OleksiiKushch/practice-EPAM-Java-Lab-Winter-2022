package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;

/**
 * @author Oleksii Kushch
 */
public class PutProductToCartCmd implements Command {
    public static final String FULL_KEY = "--put-to-cart";
    public static final String SHORT_KEY = "-ptc";

    private static final String DESCRIPTION = "Add product to cart";

    private final CartService cartService;

    public PutProductToCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        cartService.interactivePut();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
