package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.service.CartService;
import com.epam.task4.service.ProductService;
import com.epam.task6.util.MyValidator;

public class ProductDataConsoleScannerForCart {
    private final ProductService productService;
    private final CartService cartService;

    public ProductDataConsoleScannerForCart(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCmd#execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or {@code null} if abort the entire operation
     */
    public Long inputId() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_ID_FOR_CART);
        while (true) {
            String stringId = MainApp.getContext().getScanner().nextLine();
            if (stringId.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringId.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Long id = Long.valueOf(stringId);
                if (!MyValidator.isNotNegativeOrNotZero(id)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_ID);
                } else if (productService.getProductById(id) == null) {
                    System.out.printf(ShopLiterals.MSG_PRODUCT_DOES_NOT_EXISTS, id);
                } else {
                    return id;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_ID, stringId);
            }
        }
    }

    /**
     * Reading data (amount of products) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCmd#execute()})
     * @return amount of products ({@link com.epam.task1.entity.Commodity})<br>or {@code null} if abort the entire operation
     */
    public Integer inputAmount(Long product_id) {
        Integer amountOnStock = productService.getProductById(product_id).getAmount();

        Integer amountOnCart = cartService.getContent().get(product_id);
        amountOnCart = amountOnCart != null ? amountOnCart : 0;

        System.out.printf(ShopLiterals.MSG_ENTER_PRODUCT_AMOUNT_FOR_CART, amountOnStock, amountOnCart);

        while (true) {
            String stringAmount = MainApp.getContext().getScanner().nextLine();
            if (stringAmount.equals(ShopLiterals.BACK_CMD_FULL_CAST)
                    || stringAmount.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Integer amount = Integer.valueOf(stringAmount);
                if (!MyValidator.isNotNegativeOrNotZero(amount)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT);
                } else if (amountOnStock < amount + amountOnCart) {
                    System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT, product_id, amount + amountOnCart);
                    if (amountOnCart > 0) {
                        System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CART, amount, amountOnCart);
                    }
                    System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CATALOG, amountOnStock);
                } else {
                    return amount;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_AMOUNT, stringAmount);
            }
        }
    }
}
