package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import com.epam.task4.util.ConsoleColor;

import java.util.List;

import static com.epam.task4.util.AppLiteral.SUM;

/**
 * @author Oleksii Kushch
 */
public class ViewCartCmd implements Command {
    public static final String FULL_KEY = "--cart";

    public static final String DESCRIPTION = "View the contents of the cart with its total sum";

    private static final String MSG_ALERT_CART_IS_EMPTY = ConsoleColor.YELLOW + "Cart is empty!" + ConsoleColor.RESET;

    private final CartService cartService;

    public ViewCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        cartService.initRepository();
        List<Commodity> cartContainer = cartService.getContent();
        if (cartContainer.isEmpty()) {
            System.out.println(MSG_ALERT_CART_IS_EMPTY);
        } else {
            cartContainer.forEach(product -> System.out.println(product.toStringWithAmount()));
        }
        System.out.println(SUM + cartService.getSum());
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
