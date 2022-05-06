package com.epam.task4.constants;

import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task6.create_product.strategy.manual_creating.ManualProductCreatingStrategy;

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

    String BOOK_LITERAL_TYPE = "book";
    String AUDIOBOOK_LITERAL_TYPE = "audiobook";
    String E_READER_LITERAL_TYPE = "e-reader";

    // PRODUCT STRING_FIELDS NAME
    String COMMODITY_FRONT_TITLE = "FrontTitle";

    String BOOK_TITLE = "Title";
    String BOOK_AUTHOR = "Author";
    String BOOK_LANGUAGE = "Language";

    String AUDIOBOOK_NARRATOR = "Narrator";

    String E_READER_MODEL = "Model";

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

    // COMMAND
    String BACK_CMD_FULL_CAST = "--back";
    String BACK_CMD_SHORT_CAST = "-b";

    String SKIP_CMD_FULL_CAST = "--skip";
    String SKIP_CMD_SHORT_CAST = "-s";

    // FORMATS
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String OUTPUT_FORMAT_LATEST_PRODUCTS = "%s [date last put to cart: %s];%n";

    String BASE_OUTPUT_FORMAT_LOCALE = "%s (%s) [%s]%n";

    String BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY = "(%d) '%s' '%s' [%s]";

    // SINGLET LITERALS
    String EMPTY = "";
    String SPACE = " ";
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";
    String NEWLINE = "%n";

    String SUM = "Sum:";

    String PLEASE_TRY_AGAIN = "Please try again:%n";

    // SUCCESS MESSAGES
    String MSG_SUCCESS_SET_LOCALE = "Locale (interface language) successfully set: %s%n";

    String MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY = "New product creation mode (strategy) successfully set: %s%n";

    String MSG_PUT_PRODUCT_TO_CART_SUCCESS = "Product with id: %d was successfully added to cart in amount: %d%n";
    String MSG_CHECKOUT_SUCCESS = "Checkout was successful!%n";

    // ALERT MESSAGES
    String MSG_ALERT_PRODUCT_CATALOG_FILE_NOT_FOUND = "Product catalog file not found! A new empty product catalog file has been created.%n"; // maybe warning message

    String MSG_WHEN_OPERATION_ABORT = "Abort operation successful!%n";

    String MSG_NOTHING_FOUND = "Nothing found!%n";

    String MSG_ALERT_CART_IS_EMPTY = String.format("Cart is empty, please select and add (put) the product you want to cart [Use command: %s or just %s].%n",
            PutProductToCartCmd.FULL_KEY, PutProductToCartCmd.SHORT_KEY);

    String MSG_CART_IS_EMPTY = "Cart is empty!%n";
    String MSG_CART_HISTORY_IS_EMPTY = "Cart history is empty! No product has been added to the cart yet.%n";
    String MSG_ORDER_CATALOG_IS_EMPTY = "Order catalog is empty!%n";
    String MSG_PRODUCT_CATALOG_IS_EMPTY = "Product catalog is empty!%n";

    // WARNING MESSAGES
    String MSG_INVALID_INPUT_LOCALE = "Invalid input locale (interface language): '%s', choose from existing locales:%n";

    String MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY = "Invalid input mode (strategy) new product creation: '%s', " +
            "choose from following commands corresponding existing modes (strategies) of product creation:%n";

    String MSG_UNSUPPORTED_COMMAND = "Unsupported command!%n";

    String MSG_INVALID_FORMAT_YEAR = "Invalid format year '%s', (valid format, number of year, example: 2022), try again:%n";

    String MSG_INVALID_FORMAT_MONTH = "Invalid format month '%s', try again:%n";
    String MSG_INVALID_NUMERIC_FORMAT_MONTH = "Invalid value for month of year (valid values 1 - 12): %d, try again:%n";

    String MSG_INVALID_FORMAT_DAY = "Invalid format day '%s', try again:%n";
    String MSG_INVALID_NUMERIC_FORMAT_DAY = "Invalid value for DayOfMonth (valid values 1 - 28/31): %d, try again:%n";

    String MSG_PRODUCT_DOES_NOT_EXISTS = "Product with this id: %d does not exists, try again:%n";

    String MSG_INVALID_FORMAT_ID = "Invalid format id '%s', try again:%n";
    String MSG_INVALID_NUMERIC_FORMAT_ID = "Invalid format id (cannot be negative or equals zero), try again:%n";

    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT = "Too much value (amount for this product (with id: %d)): %d";
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CART = " (%d (your current choice) + %d (in your cart))";
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CATALOG = ", amount products in stock: %d, try again:%n";

    String MSG_INVALID_NUMERIC_FORMAT_AMOUNT = "Invalid format amount (cannot be negative), try again:%n";
    String MSG_INVALID_FORMAT_PRODUCT_AMOUNT = "Invalid format amount '%s', try again:%n";

    // MESSAGES
    String MSG_WHEN_INIT_LOCALE = "Select locale (interface language):%n";
    String MSG_EXISTING_LOCALES = "Existing locales (languages):%n";
    String MSG_DEFAULT_LOCALE = String.format("Default locale (interface language): %s (%s) [%s]%n", FULL_LOCALE_EN, SHORT_LOCALE_EN, NATIVE_LOCALE_EN);

    String MSG_EXISTING_PRODUCT_CREATING_STRATEGIES = "Existing modes (strategies) new product creation:%n";
    String MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY = "Select the mode of how to create a new product, on command, respectively:%n";
    String MSG_DEFAULT_PRODUCT_CREATING_STRATEGY = String.format("Default mode: \"%s\" new product creation strategy%n", ManualProductCreatingStrategy.NAME);

    String MSG_WHEN_APP_RUN = "Application is start! (shop is opening).%n" +
            "Enter '--help' to see a list of possible commands, or '--close' to stop the application.%n";
    String MSG_WHEN_APP_STOP = "Application is stop! (shop is closing).%n";

    String MSG_ABILITY_CANCEL_OPERATION = String.format("If you want to stop (abort) the operation type '%s' or '%s'%n",
                    BACK_CMD_FULL_CAST, BACK_CMD_SHORT_CAST);
    String MSG_ABILITY_SKIP_OPERATION = String.format("If you want to skip (default value will be automatically set) the operation type '%s' or '%s'%n",
            SKIP_CMD_FULL_CAST, SKIP_CMD_SHORT_CAST);

    String MSG_ENTER_AFTER_YEAR = "Please, enter FROM (after) date (year):%n";
    String MSG_ENTER_AFTER_MONTH = "Please, enter FROM (after) date (month number):%n";
    String MSG_ENTER_AFTER_DAY = "Please, enter FROM (after) date (day):%n";

    String MSG_ENTER_BEFORE_YEAR = "Please, enter TO (before) date (year):%n";
    String MSG_ENTER_BEFORE_MONTH = "Please, enter TO (before) date (month number):%n";
    String MSG_ENTER_BEFORE_DAY = "Please, enter TO (before) date (day):%n";

    String MSG_ENTER_NEAREST_YEAR = "Please, enter the nearest date (year):%n";
    String MSG_ENTER_NEAREST_MONTH = "Please, enter the nearest date (month number):%n";
    String MSG_ENTER_NEAREST_DAY = "Please, enter the nearest date (day):%n";

    String MSG_ENTER_PRODUCT_ID_FOR_CART = "Please, enter product id which do you want to put to cart:%n";

    String MSG_ENTER_PRODUCT_AMOUNT_FOR_CART = "Please, enter amount of this product (amount in stock - %d, amount in your cart - %d):%n";
}
