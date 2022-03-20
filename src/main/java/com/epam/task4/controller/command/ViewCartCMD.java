package com.epam.task4.controller.command;

import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import com.epam.task4.service.impl.CartServiceImpl;

/**
 * @author Oleksii Kushch
 */
public class ViewCartCMD implements Command {
    public static final String FULL_KEY = "--cart";

    public static final String DESCRIPTION = "View the contents of the cart with its total sum";

    @Override
    public void execute() {
        CartService cartService = new CartServiceImpl();
        cartService.initRepository();
        cartService.getAllProducts().forEach(product -> System.out.println(product.toStringWithAmount()));
        System.out.println("Sum: " + cartService.getSum());
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
