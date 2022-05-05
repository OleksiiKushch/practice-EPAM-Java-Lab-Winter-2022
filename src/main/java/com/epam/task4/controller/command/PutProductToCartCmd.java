package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.service.CartService;
import com.epam.task4.util.ProductDataConsoleScannerForCart;

import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class PutProductToCartCmd implements Command {
    public static final String FULL_KEY = "--put-to-cart";
    public static final String SHORT_KEY = "-ptc";

    private static final String DESCRIPTION = "Put (add) product to cart";

    private final CartService cartService;

    private final ProductDataConsoleScannerForCart productDataConsoleScannerForCart;

    public PutProductToCartCmd(CartService cartService, ProductDataConsoleScannerForCart productDataConsoleScannerForCart) {
        this.cartService = cartService;
        this.productDataConsoleScannerForCart = productDataConsoleScannerForCart;
    }

    @Override
    public void execute() {
        MainApp.printMessage(ShopLiterals.MSG_ABILITY_CANCEL_OPERATION);

        Long id = productDataConsoleScannerForCart.inputId();
        if (Objects.isNull(id)) {
            MainApp.printAlert(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }

        Integer amount = productDataConsoleScannerForCart.inputAmount(id);
        if (Objects.isNull(amount)) {
            MainApp.printAlert(ShopLiterals.MSG_WHEN_OPERATION_ABORT);
            return;
        }

        cartService.put(id, amount);
        MainApp.printSuccessMessage(ShopLiterals.MSG_PUT_PRODUCT_TO_CART_SUCCESS, id, amount);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
