package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import com.epam.task4.service.impl.CartServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class CheckoutCMD implements Command {
    public static final String FULL_KEY = "--checkout";

    public static final String DESCRIPTION = "Buy all items from the cart (checkout)";

    @Override
    public void execute() {
        CartService cartService = new CartServiceImpl();
        cartService.initRepository();
        cartService.checkout();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
