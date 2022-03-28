package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.ProductRepository;

/**
 * Class for reading data about product (like id or amount) from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see PutProductToCartCmd
 * @author Oleksii Kushch
 */
public class ProductDataScanner {
    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link PutProductToCartCmd#execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or {@code null} if abort the entire operation
     */
    public static Long inputIdForCart(ProductRepository productRepository) {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_ID);
        while (true) {
            String stringId = MainApp.getContext().getScanner().nextLine();
            if (stringId.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringId.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Long id = Long.valueOf(stringId);
                if (isNegativeOrZero(id)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_ID);
                } else if (productRepository.getById(id) == null) {
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
     * (method {@link PutProductToCartCmd#execute()})
     * @return amount of products ({@link com.epam.task1.entity.Commodity})<br>or {@code null} if abort the entire operation
     */
    public static Integer inputAmountForCart(Long id, ProductRepository productRepository, CartRepository cartRepository) {
        Integer amountOnStock = productRepository.getById(id).getAmount();

        Integer amountOnCart = cartRepository.getAll().get(id);
        amountOnCart = amountOnCart != null ? amountOnCart : 0;

        System.out.printf(ShopLiterals.MSG_ENTER_AMOUNT_PRODUCT, amountOnStock, amountOnCart);

        while (true) {
            String stringAmount = MainApp.getContext().getScanner().nextLine();
            if (stringAmount.equals(ShopLiterals.BACK_CMD_FULL_CAST)
                    || stringAmount.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Integer amount = Integer.valueOf(stringAmount);
                if (isNegativeOrZero(amount)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT_PRODUCT);
                } else if (amountOnStock < amount + amountOnCart) {
                    System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_AMOUNT_PRODUCT, id, amount + amountOnCart);
                    if (amountOnCart > 0) {
                        System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_AMOUNT_IN_CART_PRODUCT, amount, amountOnCart);
                    }
                    System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_AMOUNT_IN_STOCK_PRODUCT, amountOnStock);
                } else {
                    return amount;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_AMOUNT_PRODUCT, stringAmount);
            }
        }
    }

    private static boolean isNegativeOrZero(Number number) {
        return number.longValue() <= 0;
    }
}
