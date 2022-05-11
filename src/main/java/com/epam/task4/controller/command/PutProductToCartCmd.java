package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.Command;
import com.epam.task4.exception.AbortOperationException;
import com.epam.task4.service.CartService;
import com.epam.task4.util.ProductDataConsoleScannerForCart;
import com.epam.task7.constants.MessageKey;

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
        MainApp.printMessage(MessageKey.MSG_KEY_ABILITY_CANCEL_OPERATION, ShopLiterals.BACK_CMD_FULL_CAST, ShopLiterals.BACK_CMD_SHORT_CAST);

        Long id;
        Integer amount;
        try {
            id = productDataConsoleScannerForCart.inputId();
            amount = productDataConsoleScannerForCart.inputAmount(id);
        } catch (AbortOperationException exception) {
            MainApp.printAlert(MessageKey.MSG_KEY_WHEN_OPERATION_ABORT);
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
