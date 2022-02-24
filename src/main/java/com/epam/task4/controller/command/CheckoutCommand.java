package com.epam.task4.controller.command;

import com.epam.task4.util.Cart;
import com.epam.task4.controller.Command;

public class CheckoutCommand implements Command {
    @Override
    public void execute() {
        Cart.getInstance().checkout();
    }

    @Override
    public String getDescription() {
        return "Buy all items from the cart (checkout)";
    }
}
