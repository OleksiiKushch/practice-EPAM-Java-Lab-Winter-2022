package com.epam.task4.controller.command;

import com.epam.task4.MainApp;
import com.epam.task4.controller.Command;
import com.epam.task4.mockdata.MockProductCatalog;
import com.epam.task4.util.Cart;

/**
 * @author Oleksii Kushch
 */
public class PutProductToCartCommand implements Command {
    @Override
    public void execute() {
        System.out.println("If you want to stop (abort) the operation type '--back' or '-b'");
        Long id = inputId();
        if (id == null) { return; }   // abort the entire operation
        Integer amount = inputAmount();
        if (amount == null) { return; }   // abort the entire operation

        Cart.getInstance().put(id, amount);
        System.out.println("Product with id: " + id + " was successfully added to cart in amount: " + amount);
    }

    @Override
    public String getDescription() {
        return "Add product to cart";
    }

    /**
     * Reading data (product id) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation (method {@link #execute()})
     * @return product ({@link com.epam.task1.entity.Commodity}) id<br>or {@code null} if abort the entire operation
     */
    private Long inputId() {
        System.out.println("Please, enter id product which do you want add to cart:");
        Long id = null;
        boolean isIdSet = false;
        while (!isIdSet) {
            String stringId = MainApp.SCANNER.nextLine();
            if (stringId.equals("--back") || stringId.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                id = Long.valueOf(stringId);
                if (!isNotNegativeAndNotZero(id)) {
                    System.out.println("Invalid format id (cannot be negative or equals zero), try again:");
                } else if (!MockProductCatalog.getInstance().isContainsProduct(id)) {
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
     * Noted: return {@code null} if needed abort the entire operation (method {@link #execute()})
     * @return amount of products ({@link com.epam.task1.entity.Commodity})<br>or {@code null} if abort the entire operation
     */
    private Integer inputAmount() {
        System.out.println("Please, enter amount of this product:");
        Integer amount = null;
        boolean isAmountSet = false;
        while (!isAmountSet) {
            String stringAmount = MainApp.SCANNER.nextLine();
            if (stringAmount.equals("--back") || stringAmount.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                amount = Integer.valueOf(stringAmount);
                if (isNotNegativeAndNotZero(amount)) {
                    isAmountSet = true;
                } else {
                    System.out.println("Invalid format amount (cannot be negative or equals zero, try again:");
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format amount '" + stringAmount + "', try again:");
            }
        }
        return amount;
    }

    private boolean isNotNegativeAndNotZero(Number number) {
        return number.longValue() > 0;
    }
}
