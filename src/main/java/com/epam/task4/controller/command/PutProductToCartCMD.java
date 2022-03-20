package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import com.epam.task4.service.impl.CartServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class PutProductToCartCMD implements Command {
    public static final String FULL_KEY = "--put-to-cart";
    public static final String SHORT_KEY = "-ptc";

    public static final String DESCRIPTION = "Add product to cart";

    @Override
    public void execute() {
        CartService cartService = new CartServiceImpl();
        cartService.initRepository();
        cartService.interactivePut();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
