package com.epam.task4.controller.command;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public class ViewCartCmd implements Command {
    public static final String FULL_KEY = "--cart";

    private static final String DESCRIPTION = "Display the contents of the cart with its total sum";

    private final CartService cartService;

    public ViewCartCmd(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        List<Commodity> cartContainer = cartService.getContentList();
        if (cartContainer.isEmpty()) {
            MainApp.printAlert(ShopLiterals.MSG_CART_IS_EMPTY);
        } else {
            cartContainer.forEach(product -> System.out.println(product.toStringWithAmount()));
        }
        MainApp.print(StringUtils.join(ShopLiterals.SUM, ShopLiterals.SPACE, cartService.getSum(), ShopLiterals.NEWLINE));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
