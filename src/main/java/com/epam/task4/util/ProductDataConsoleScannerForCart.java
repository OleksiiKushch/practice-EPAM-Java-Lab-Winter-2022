package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.exception.AbortOperationException;
import com.epam.task4.service.CartService;
import com.epam.task4.service.ProductService;
import com.epam.task6.util.NumericValidator;

import java.util.Objects;
import java.util.Scanner;

public class ProductDataConsoleScannerForCart {
    private final ProductService productService;
    private final CartService cartService;
    private final Scanner scanner;

    public ProductDataConsoleScannerForCart(ProductService productService, CartService cartService, Scanner scanner) {
        this.productService = productService;
        this.cartService = cartService;
        this.scanner = scanner;
    }

    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: throw {@link com.epam.task4.exception.AbortOperationException} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCmd#execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or
     * throw {@link com.epam.task4.exception.AbortOperationException} if abort the entire operation
     */
    public Long inputId() {
        MainApp.printMessage(ShopLiterals.MSG_ENTER_PRODUCT_ID_FOR_CART);
        while (true) {
            String stringId = scanner.nextLine();
            if (ShopLiterals.BACK_CMD_FULL_CAST.equals(stringId) ||
                    ShopLiterals.BACK_CMD_SHORT_CAST.equals(stringId)) {
                throw new AbortOperationException();
            }
            try {
                Long id = Long.valueOf(stringId);
                if (!NumericValidator.isNotNegativeOrNotZero(id)) {
                    MainApp.printWarning(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_ID);
                } else if (Objects.isNull(productService.getProductById(id))) {
                    MainApp.printWarning(ShopLiterals.MSG_PRODUCT_DOES_NOT_EXISTS, id);
                } else {
                    return id;
                }
            } catch(NumberFormatException exception) {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_FORMAT_ID, stringId);
            }
        }
    }

    /**
     * Reading data (amount of products) from the console with built-in validation.
     * <p>
     * Noted: throw {@link com.epam.task4.exception.AbortOperationException} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCmd#execute()})
     * @return amount of products ({@link com.epam.task1.entity.Commodity})<br>or
     * throw {@link com.epam.task4.exception.AbortOperationException} if abort the entire operation
     */
    public Integer inputAmount(Long product_id) {
        Integer amountOnStock = productService.getProductById(product_id).getAmount();

        Integer amountOnCart = cartService.getContent().get(product_id);
        amountOnCart = amountOnCart != null ? amountOnCart : 0;

        MainApp.printMessage(ShopLiterals.MSG_ENTER_PRODUCT_AMOUNT_FOR_CART, amountOnStock, amountOnCart);

        while (true) {
            String stringAmount = scanner.nextLine();
            if (ShopLiterals.BACK_CMD_FULL_CAST.equals(stringAmount)
                    || ShopLiterals.BACK_CMD_SHORT_CAST.equals(stringAmount)) {
                throw new AbortOperationException();
            }
            try {
                Integer amount = Integer.valueOf(stringAmount);
                if (!NumericValidator.isNotNegativeOrNotZero(amount)) {
                    MainApp.printWarning(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT);
                } else if (amountOnStock < amount + amountOnCart) {
                    MainApp.printWarning(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT, product_id, amount + amountOnCart);
                    if (amountOnCart > 0) {
                        MainApp.printWarning(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CART, amount, amountOnCart);
                    }
                    MainApp.printWarning(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CATALOG, amountOnStock);
                } else {
                    return amount;
                }
            } catch(NumberFormatException exception) {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_AMOUNT, stringAmount);
            }
        }
    }
}
