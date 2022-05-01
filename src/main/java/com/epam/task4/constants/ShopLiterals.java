package com.epam.task4.constants;

/**
 * @author Oleksii Kushch
 */
public interface ShopLiterals {
    // LOCALES
    String SHORT_LOCALE_EN = "en";
    String FULL_LOCALE_EN = "english";
    String NATIVE_LOCALE_EN = "English";

    String SHORT_LOCALE_RU = "ru";
    String FULL_LOCALE_RU = "russian";
    String NATIVE_LOCALE_RU = "Русский";

    // ENTITIES
    String KEY_PRODUCT_ID = "PRODUCT_ID";
    String KEY_PRODUCT_FRONT_TITLE = "PRODUCT_FRONT_TITLE";
    String KEY_PRODUCT_PRICE = "PRODUCT_PRICE";
    String KEY_PRODUCT_AMOUNT = "PRODUCT_AMOUNT";

    String KEY_BOOK_TITLE = "BOOK_TITLE";
    String KEY_BOOK_AUTHOR = "BOOK_AUTHOR";
    String KEY_BOOK_LANGUAGE = "BOOK_LANGUAGE";
    String KEY_BOOK_NUMBER_OF_PAGES = "BOOK_NUMBER_OF_PAGES";

    String KEY_AUDIOBOOK_FILE_SIZE_MB = "AUDIOBOOK_FILE_SIZE_MB";
    String KEY_AUDIOBOOK_LISTENING_TIME_MINUTES = "AUDIOBOOK_LISTENING_TIME_MINUTES";
    String KEY_AUDIOBOOK_NARRATOR = "AUDIOBOOK_NARRATOR";

    String KEY_E_READER_MODEL = "E_READER_MODEL";
    String KEY_E_READER_DISPLAY_SIZE_INCHES = "E_READER_DISPLAY_SIZE_INCHES";
    String KEY_E_READER_STORAGE_CAPACITY_GB = "E_READER_STORAGE_CAPACITY_GB";
    String KEY_E_READER_SCREEN_RESOLUTION_PPI = "E_READER_SCREEN_RESOLUTION_PPI";

    String BOOK_LITERAL_TYPE = "book";
    String AUDIOBOOK_LITERAL_TYPE = "audiobook";
    String E_READER_LITERAL_TYPE = "e-reader";

    // PRODUCT CREATING STRATEGY
    String MANUAL_PRODUCT_CREATING_STRATEGY = "manual";
    String AUTO_PRODUCT_CREATING_STRATEGY = "automatic";

    // COMMAND
    String BACK_CMD_FULL_CAST = "--back";
    String BACK_CMD_SHORT_CAST = "-b";

    String SKIP_CMD_FULL_CAST = "--skip";
    String SKIP_CMD_SHORT_CAST = "-s";

    // FORMATS
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String OUTPUT_FORMAT_LATEST_PRODUCTS = "%s [date last put to cart: %s];%n";

    String BASE_OUTPUT_FORMAT_LOCALE = "%s (%s) [%s]%n";

    String BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY = "(%d) '%s' '%s' %s";

    String HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY = "'%d', '%s' and '%s' for %s";

    // SINGLET LITERALS
    String SPACE = " ";
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";

    String SUM = "Sum:";

    String TRY_AGAIN = ConsoleColor.RED + "try again:" + ConsoleColor.RESET;

    // SUCCESS MESSAGES
    String MSG_SUCCESS_SET_LOCALE = ConsoleColor.GREEN +
            "Locale (interface language) successfully set: %s%n" + ConsoleColor.RESET;

    String MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY = ConsoleColor.GREEN +
            "New product creation mode (strategy) successfully set: %s%n" + ConsoleColor.RESET;

    String MSG_PUT_PRODUCT_TO_CART_SUCCESS = ConsoleColor.GREEN +
            "Product with id: %d was successfully added to cart in amount: %d%n" + ConsoleColor.RESET;
    String MSG_CHECKOUT_SUCCESS = ConsoleColor.GREEN + "Checkout was successful!" + ConsoleColor.RESET;


    // ALERT MESSAGES
    String MSG_ALERT_PRODUCT_CATALOG_FILE_NOT_FOUND = ConsoleColor.YELLOW +
            "Product catalog file not found! A new empty product catalog file has been created." + ConsoleColor.RESET; // maybe warning message

    String MSG_WHEN_OPERATION_ABORT = ConsoleColor.YELLOW + "Abort operation successful!" + ConsoleColor.RESET;

    String MSG_NOTHING_FOUND = ConsoleColor.YELLOW + "Nothing found!" + ConsoleColor.RESET;

    String MSG_ALERT_CART_IS_EMPTY = ConsoleColor.YELLOW +
            "Cart is empty, please select and add (put) the product you want to cart." + ConsoleColor.RESET;

    String MSG_CART_IS_EMPTY = ConsoleColor.YELLOW + "Cart is empty!" + ConsoleColor.RESET;
    String MSG_CART_HISTORY_IS_EMPTY = ConsoleColor.YELLOW +
            "Cart history is empty! No product has been added to the cart yet." + ConsoleColor.RESET;
    String MSG_ORDER_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Order catalog is empty!" + ConsoleColor.RESET;
    String MSG_PRODUCT_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Product catalog is empty!" + ConsoleColor.RESET;

    // WARNING MESSAGES
    String MSG_INVALID_INPUT_LOCALE = ConsoleColor.RED +
            "Invalid input locale (interface language): '%s', choose from existing locales:%n" + ConsoleColor.RESET;

    String MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY = ConsoleColor.RED +
            "Invalid input mode (strategy) new product creation: '%s', choose from:%n%s%n%s%n" + ConsoleColor.RESET;

    String MSG_UNSUPPORTED_COMMAND = ConsoleColor.RED + "Unsupported command!" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_YEAR = ConsoleColor.RED +
            "Invalid format year '%s', (valid format, number of year, example: 2022), try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_MONTH = ConsoleColor.RED + "Invalid format month '%s', try again:%n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_MONTH = ConsoleColor.RED +
            "Invalid value for month of year (valid values 1 - 12): %d, try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_DAY = ConsoleColor.RED + "Invalid format day '%s', try again:%n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_DAY = ConsoleColor.RED +
            "Invalid value for DayOfMonth (valid values 1 - 28/31): %d, try again:%n" + ConsoleColor.RESET;

    String MSG_PRODUCT_DOES_NOT_EXISTS = ConsoleColor.RED +
            "Product with this id: %d does not exists, try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_FORMAT_ID = ConsoleColor.RED + "Invalid format id '%s', try again:%n" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_ID = ConsoleColor.RED +
            "Invalid format id (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;

    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT = ConsoleColor.RED +
            "Too much value (amount for this product (with id: %d)): %d" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CART = ConsoleColor.RED +
            " (%d (your current choice) + %d (in your cart))" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CATALOG = ConsoleColor.RED +
            ", amount products in stock: %d, try again:%n" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_AMOUNT = ConsoleColor.RED +
            "Invalid format amount (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_PRODUCT_AMOUNT = ConsoleColor.RED + "Invalid format amount '%s', try again:%n" + ConsoleColor.RESET;

    // MESSAGES
    String MSG_WHEN_INIT_LOCALE = ConsoleColor.CYAN + "Select locale (interface language): " + ConsoleColor.RESET;
    String MSG_EXISTING_LOCALES = ConsoleColor.CYAN + "Existing locales (languages): " + ConsoleColor.RESET;
    String MSG_DEFAULT_LOCALE = ConsoleColor.CYAN +
            String.format("Default locale (interface language): %s (%s) [%s]", FULL_LOCALE_EN, SHORT_LOCALE_EN, NATIVE_LOCALE_EN) + ConsoleColor.RESET;

    String MSG_EXISTING_MODE_CREATING_PRODUCT = ConsoleColor.CYAN + "Existing mode (strategy) new product creation:" + ConsoleColor.RESET;;
    String MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY = ConsoleColor.CYAN +
            "Select the mode of how to create a new product, on command, respectively:%n" + MSG_EXISTING_MODE_CREATING_PRODUCT
            + ConsoleColor.RESET + "%n%s%n%s%n";
    String MSG_DEFAULT_PRODUCT_CREATING_STRATEGY = ConsoleColor.CYAN +
            String.format("Default mode: \"%s\" new product creation strategy", MANUAL_PRODUCT_CREATING_STRATEGY) + ConsoleColor.RESET;

    String MSG_WHEN_APP_RUN = ConsoleColor.CYAN + "Application is start! (shop is opening).\n" +
            "Enter '--help' to see a list of possible commands, or '--close' to stop the application." + ConsoleColor.RESET;
    String MSG_WHEN_APP_STOP = ConsoleColor.CYAN + "Application is stop! (shop is closing)." + ConsoleColor.RESET;

    String MSG_ABILITY_CANCEL_OPERATION = ConsoleColor.CYAN +
            String.format("If you want to stop (abort) the operation type '%s' or '%s'",
                    BACK_CMD_FULL_CAST, BACK_CMD_SHORT_CAST) + ConsoleColor.RESET;
    String MSG_ABILITY_SKIP_OPERATION = ConsoleColor.CYAN +
            String.format("If you want to skip (default value will be automatically set) the operation type '%s' or '%s'",
                    SKIP_CMD_FULL_CAST, SKIP_CMD_SHORT_CAST) + ConsoleColor.RESET;

    String MSG_ENTER_AFTER_YEAR = ConsoleColor.CYAN + "Please, enter FROM (after) date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_AFTER_MONTH = ConsoleColor.CYAN + "Please, enter FROM (after) date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_AFTER_DAY = ConsoleColor.CYAN + "Please, enter FROM (after) date (day):" + ConsoleColor.RESET;

    String MSG_ENTER_BEFORE_YEAR = ConsoleColor.CYAN + "Please, enter TO (before) date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_BEFORE_MONTH = ConsoleColor.CYAN + "Please, enter TO (before) date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_BEFORE_DAY = ConsoleColor.CYAN + "Please, enter TO (before) date (day):" + ConsoleColor.RESET;

    String MSG_ENTER_NEAREST_YEAR = ConsoleColor.CYAN + "Please, enter the nearest date (year):" + ConsoleColor.RESET;
    String MSG_ENTER_NEAREST_MONTH = ConsoleColor.CYAN + "Please, enter the nearest date (month number):" + ConsoleColor.RESET;
    String MSG_ENTER_NEAREST_DAY = ConsoleColor.CYAN + "Please, enter the nearest date (day):" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_ID_FOR_CART = ConsoleColor.CYAN +
            "Please, enter product id which do you want to put to cart:" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_AMOUNT_FOR_CART = ConsoleColor.CYAN +
            "Please, enter amount of this product (amount in stock - %d, amount in your cart - %d):%n" + ConsoleColor.RESET;
}
