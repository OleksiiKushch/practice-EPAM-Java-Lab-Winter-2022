package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.dal.CartRepository;
import com.epam.task4.dal.ProductRepository;

import java.util.Map;

/**
 * Class for reading data about product (like id or amount) from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see com.epam.task4.controller.command.PutProductToCartCMD
 * @author Oleksii Kushch
 */
public class ProductDataScanner {
    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCMD#execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or {@code null} if abort the entire operation
     */
    public static Long inputId(ProductRepository productRepository) {
        System.out.println("Please, enter id product which do you want add to cart:");
        Long id = null;
        boolean isIdSet = false;
        while (!isIdSet) {
            String stringId = MainApp.getScanner().nextLine();
            if (stringId.equals("--back") || stringId.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                id = Long.valueOf(stringId);
                if (isNegativeOrZero(id)) {
                    System.out.println("Invalid format id (cannot be negative or equals zero), try again:");
                } else if (productRepository.getById(id) == null) {
                    System.out.println("Product with this id: " + id + " does not exists, try again:");
                } else {
                    isIdSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format id '" + stringId + "', try again:");
            }
        }
        return id;
    }

    /**
     * Reading data (amount of products) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method {@link com.epam.task4.controller.command.PutProductToCartCMD#execute()})
     * @return amount of products ({@link com.epam.task1.entity.Commodity})<br>or {@code null} if abort the entire operation
     */
    public static Integer inputAmount(Long id, ProductRepository productRepository, CartRepository cartRepository) {
        Integer amountOnStock = productRepository.getById(id).getAmount();
        Map<Long, Integer> cartContainer = cartRepository.getAll();
        Integer amountOnCart = cartContainer.get(id);
        if (amountOnCart == null) {
            amountOnCart = 0;
        }

        System.out.println("Please, enter amount of this product (amount on stock - " + amountOnStock +
                ", amount on your cart - " + amountOnCart + "):");

        Integer amount = null;
        boolean isAmountSet = false;
        while (!isAmountSet) {
            String stringAmount = MainApp.getScanner().nextLine();
            if (stringAmount.equals("--back") || stringAmount.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                amount = Integer.valueOf(stringAmount);
                if (isNegativeOrZero(amount)) {
                    System.out.println("Invalid format amount (cannot be negative or equals zero, try again:");
                } else if (amountOnStock < amount + amountOnCart) {
                    System.out.print("Too much value (amount for this product (with id: " + id + ")): "
                            + Math.addExact(amount, amountOnCart));
                    if (amountOnCart > 0) {
                        System.out.print(" (" + amount + " + " + amountOnCart + " (in your cart))");
                    }
                    System.out.println(", amount products in stock: " + amountOnStock + ", try again:");
                } else {
                    isAmountSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format amount '" + stringAmount + "', try again:");
            }
        }
        return amount;
    }

    private static boolean isNegativeOrZero(Number number) {
        return number.longValue() <= 0;
    }
}
