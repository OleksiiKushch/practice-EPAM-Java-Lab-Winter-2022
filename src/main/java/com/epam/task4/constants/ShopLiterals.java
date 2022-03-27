package com.epam.task4.constants;

/**
 * @author Oleksii Kushch
 */
public interface ShopLiterals {
    String BACK_CMD_FULL_CAST = "--back";
    String BACK_CMD_SHORT_CAST = "-b";

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String SPACE = " ";
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";

    String SUM = "Sum:";

    String MSG_NOTHING_FOUND = ConsoleColor.YELLOW + "Nothing found!" + ConsoleColor.RESET;

    String MSG_WHEN_OPERATION_ABORT = ConsoleColor.YELLOW + "Abort operation successful!" + ConsoleColor.RESET;
    String MSG_ABILITY_CANCEL_OPERATION = ConsoleColor.CYAN +
            "If you want to stop (abort) the operation type '--back' or '-b'" + ConsoleColor.RESET;

    String MSG_WHEN_APP_RUN = ConsoleColor.CYAN + "Application is start! (shop is opening).\n" +
            "Enter '--help' to see a list of possible commands, or '--stop' to stop the application." + ConsoleColor.RESET;
    String MSG_UNSUPPORTED_COMMAND = ConsoleColor.RED + "Unsupported command" + ConsoleColor.RESET;

    String MSG_WHEN_APP_STOP = ConsoleColor.CYAN + "Application is stop! (shop is closing)." + ConsoleColor.RESET;

    String MSG_CART_IS_EMPTY = ConsoleColor.YELLOW + "Cart is empty!" + ConsoleColor.RESET;

    String MSG_HISTORY_IS_EMPTY = ConsoleColor.YELLOW +
            "Cart history is empty! No product has been added to the cart yet." + ConsoleColor.RESET;

    String MSG_ORDER_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Order catalog is empty!" + ConsoleColor.RESET;

    String MSG_PRODUCT_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Product catalog is empty!" + ConsoleColor.RESET;

    String OUTPUT_FORMAT_LATEST_PRODUCTS = "%s [date last put to cart: %s];%n";

    String MSG_ALERT_CART_IS_EMPTY = ConsoleColor.RED +
            "Cart is empty, please select and add the product you want to cart." + ConsoleColor.RESET;
    String MSG_CHECKOUT_SUCCESS = ConsoleColor.GREEN + "Checkout was successful!" + ConsoleColor.RESET;
    String MSG_PUT_PRODUCT_TO_CART_SUCCESS = ConsoleColor.GREEN +
            "Product with id: %d was successfully added to cart in amount: %d%n" + ConsoleColor.RESET;

    String MSG_ENTER_AFTER_YEAR = ConsoleColor.CYAN + "Please, enter FROM (after) date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_AFTER_MONTH = ConsoleColor.CYAN + "Please, enter FROM (after) date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_AFTER_DAY = ConsoleColor.CYAN + "Please, enter FROM (after) date (day):" + ConsoleColor.RESET;

    String MSG_ENTER_BEFORE_YEAR = ConsoleColor.CYAN + "Please, enter TO (before) date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_BEFORE_MONTH = ConsoleColor.CYAN + "Please, enter TO (before) date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_BEFORE_DAY = ConsoleColor.CYAN + "Please, enter TO (before) date (day):" + ConsoleColor.RESET;

    String MSG_ENTER_NEAREST_YEAR = ConsoleColor.CYAN + "Please, enter the nearest date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_NEAREST_MONTH = ConsoleColor.CYAN + "Please, enter the nearest date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_NEAREST_DAY = ConsoleColor.CYAN + "Please, enter the nearest date (day):" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_YEAR = ConsoleColor.RED +
            "Invalid format year '%s', (valid format, number of year, example: 2022), try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_MONTH = ConsoleColor.RED + "Invalid format month '%s', try again:&n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_MONTH = ConsoleColor.RED +
            "Invalid value for month of year (valid values 1 - 12): %d, try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_DAY = ConsoleColor.RED + "Invalid format day '%s', try again:%n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_DAY = ConsoleColor.RED +
            "Invalid value for DayOfMonth (valid values 1 - 28/31): %d, try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_ID = ConsoleColor.RED + "Invalid format id '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_ID = ConsoleColor.CYAN +
            "Please, enter product id which do you want to put to cart:" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_ID = ConsoleColor.RED +
            "Invalid format id (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;
    String MSG_PRODUCT_DOES_NOT_EXISTS = ConsoleColor.RED +
            "Product with this id: %d does not exists, try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_AMOUNT_PRODUCT = ConsoleColor.CYAN +
            "Please, enter amount of this product (amount in stock - %d, amount in your cart - %d):%n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_AMOUNT_PRODUCT = ConsoleColor.RED +
            "Invalid format amount (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_AMOUNT_PRODUCT = ConsoleColor.RED +
            "Too much value (amount for this product (with id: %d)): %d" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_AMOUNT_IN_CART_PRODUCT = ConsoleColor.RED +
            " (%d (your current choice) + %d (in your cart))" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_AMOUNT_IN_STOCK_PRODUCT = ConsoleColor.RED +
            ", amount products in stock: %d, try again:%n" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_AMOUNT_PRODUCT = ConsoleColor.RED + "Invalid format amount '%s', try again:%n" + ConsoleColor.RESET;
}
