package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewCartCmd implements Command {
    public static final String FULL_KEY = "--cart";

    public static final String DESCRIPTION = "View the contents of the cart with its total sum";

    private final CartService cartService;

    public ViewCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        List<Commodity> cartContainer = cartService.getContent();
        if (cartContainer.isEmpty()) {
            System.out.println(ShopLiterals.MSG_CART_IS_EMPTY);
        } else {
            cartContainer.forEach(product -> System.out.println(product.toStringWithAmount()));
        }
        System.out.println(ShopLiterals.SUM + ShopLiterals.SPACE + cartService.getSum());
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
