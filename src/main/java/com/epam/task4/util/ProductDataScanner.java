package com.epam.task4.util;

import com.epam.task4.MainApp;
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
    private static final String MSG_ENTER_ID_PRODUCT = ConsoleColor.CYAN +
            "Please, enter id product which do you want add to cart:" + ConsoleColor.RESET;
    private static final String MSG_INVALID_NUMERIC_FORMAT_ID_PRODUCT = ConsoleColor.RED +
            "Invalid format id (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;
    private static final String MSG_PRODUCT_DOES_NOT_EXISTS = ConsoleColor.RED +
            "Product with this id: %d does not exists, try again:%n" + ConsoleColor.RESET;
    private static final String MSG_INVALID_FORMAT_ID_PRODUCT = ConsoleColor.RED +
            "Invalid format id '%s', try again:%n" + ConsoleColor.RESET;

    private static final String MSG_ENTER_AMOUNT_PRODUCT = ConsoleColor.CYAN +
            "Please, enter amount of this product (amount in stock - %d, amount in your cart - %d):%n" + ConsoleColor.RESET;
    private static final String MSG_INVALID_NUMERIC_FORMAT_AMOUNT_PRODUCT = ConsoleColor.RED +
            "Invalid format amount (cannot be negative or equals zero, try again:" + ConsoleColor.RESET;
    private static final String MSG_TOO_MUCH_VALUE_AMOUNT_PRODUCT = ConsoleColor.RED +
            "Too much value (amount for this product (with id: %d)): %d" + ConsoleColor.RESET;
    private static final String MSG_TOO_MUCH_VALUE_AMOUNT_IN_CART_PRODUCT = ConsoleColor.RED +
            " (%d (your current choice) + %d (in your cart))" + ConsoleColor.RESET;
    private static final String MSG_TOO_MUCH_VALUE_AMOUNT_IN_STOCK_PRODUCT = ConsoleColor.RED +
            ", amount products in stock: %d, try again:%n" + ConsoleColor.RESET;
    private static final String MSG_INVALID_FORMAT_AMOUNT_PRODUCT = ConsoleColor.RED +
            "Invalid format amount '%s', try again:" + ConsoleColor.RESET;

    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link PutProductToCartCmd#execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or {@code null} if abort the entire operation
     */
    public static Long inputId(ProductRepository productRepository) {
        System.out.println(MSG_ENTER_ID_PRODUCT);
        Long id = null;
        boolean isIdSet = false;
        while (!isIdSet) {
            String stringId = MainApp.getScanner().nextLine();
            if (stringId.equals(AppLiteral.BACK_CMD_FULL_CAST) ||
                    stringId.equals(AppLiteral.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                id = Long.valueOf(stringId);
                if (isNegativeOrZero(id)) {
                    System.out.println(MSG_INVALID_NUMERIC_FORMAT_ID_PRODUCT);
                } else if (productRepository.getById(id) == null) {
                    System.out.printf(MSG_PRODUCT_DOES_NOT_EXISTS, id);
                } else {
                    isIdSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(MSG_INVALID_FORMAT_ID_PRODUCT, stringId);
            }
        }
        return id;
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

        System.out.printf(MSG_ENTER_AMOUNT_PRODUCT, amountOnStock, amountOnCart);

        Integer amount = null;
        boolean isAmountSet = false;
        while (!isAmountSet) {
            String stringAmount = MainApp.getScanner().nextLine();
            if (stringAmount.equals(AppLiteral.BACK_CMD_FULL_CAST)
                    || stringAmount.equals(AppLiteral.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                amount = Integer.valueOf(stringAmount);
                if (isNegativeOrZero(amount)) {
                    System.out.println(MSG_INVALID_NUMERIC_FORMAT_AMOUNT_PRODUCT);
                } else if (amountOnStock < amount + amountOnCart) {
                    System.out.printf(MSG_TOO_MUCH_VALUE_AMOUNT_PRODUCT, id, amount + amountOnCart);
                    if (amountOnCart > 0) {
                        System.out.printf(MSG_TOO_MUCH_VALUE_AMOUNT_IN_CART_PRODUCT, amount, amountOnCart);
                    }
                    System.out.printf(MSG_TOO_MUCH_VALUE_AMOUNT_IN_STOCK_PRODUCT, amountOnStock);
                } else {
                    isAmountSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(MSG_INVALID_FORMAT_AMOUNT_PRODUCT, stringAmount);
            }
        }
        return amount;
    }

    private static boolean isNegativeOrZero(Number number) {
        return number.longValue() <= 0;
    }
}
