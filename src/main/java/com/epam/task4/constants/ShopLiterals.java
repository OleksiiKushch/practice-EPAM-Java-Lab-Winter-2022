package com.epam.task4.constants;

/**
 * @author Oleksii Kushch
 */
public interface ShopLiterals {
    String BOOK_LITERAL_TYPE = "book";
    String AUDIOBOOK_LITERAL_TYPE = "audiobook";
    String E_READER_LITERAL_TYPE = "e-reader";

    String MANUAL_PRODUCT_CREATING_STRATEGY = "manual";
    String AUTO_PRODUCT_CREATING_STRATEGY = "automatic";

    String BACK_CMD_FULL_CAST = "--back";
    String BACK_CMD_SHORT_CAST = "-b";

    String SKIP_CMD_FULL_CAST = "--skip";
    String SKIP_CMD_SHORT_CAST = "-s";

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String SPACE = " ";
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";

    String SUM = "Sum:";

    String OUTPUT_FORMAT_LATEST_PRODUCTS = "%s [date last put to cart: %s];%n";
    String BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY = "(%d) '%s' '%s' %s";
    String HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY = "'%d', '%s' and '%s' for %s";

    String MSG_ALERT_PRODUCT_CATALOG_FILE_NOT_FOUND = ConsoleColor.YELLOW +
            "Product catalog file not found! A new empty product catalog file has been created." + ConsoleColor.RESET;

    String MSG_WHEN_INIT_PRODUCT_CREATING_STRATEGY = ConsoleColor.CYAN +
            "Select the mode of how to create a new product, on command, respectively:%n%s%n%s%n" + ConsoleColor.RESET;
    String MSG_DEFAULT_PRODUCT_CREATING_STRATEGY = ConsoleColor.CYAN +
            String.format("Default mode: \"%s\" new product creation strategy", MANUAL_PRODUCT_CREATING_STRATEGY) + ConsoleColor.RESET;

    String MSG_SUCCESS_SET_PRODUCT_CREATING_STRATEGY = ConsoleColor.GREEN +
            "New product creation mode (strategy) successfully set: %s%n" + ConsoleColor.RESET;

    String MSG_INVALID_INPUT_PRODUCT_CREATING_STRATEGY = ConsoleColor.RED +
            "Invalid input mode (strategy) new product creation: '%s', choose from:%n%s%n%s%ntry again:%n" + ConsoleColor.RESET;

    String MSG_UNSUPPORTED_COMMAND = ConsoleColor.RED + "Unsupported command!" + ConsoleColor.RESET;
    String MSG_ALERT_CART_IS_EMPTY = ConsoleColor.RED +
            "Cart is empty, please select and add the product you want to cart." + ConsoleColor.RESET;

    String MSG_NOTHING_FOUND = ConsoleColor.YELLOW + "Nothing found!" + ConsoleColor.RESET;
    String MSG_WHEN_OPERATION_ABORT = ConsoleColor.YELLOW + "Abort operation successful!" + ConsoleColor.RESET;
    String MSG_CART_IS_EMPTY = ConsoleColor.YELLOW + "Cart is empty!" + ConsoleColor.RESET;
    String MSG_CART_HISTORY_IS_EMPTY = ConsoleColor.YELLOW +
            "Cart history is empty! No product has been added to the cart yet." + ConsoleColor.RESET;
    String MSG_ORDER_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Order catalog is empty!" + ConsoleColor.RESET;
    String MSG_PRODUCT_CATALOG_IS_EMPTY = ConsoleColor.YELLOW + "Product catalog is empty!" + ConsoleColor.RESET;

    String MSG_CHECKOUT_SUCCESS = ConsoleColor.GREEN + "Checkout was successful!" + ConsoleColor.RESET;
    String MSG_PUT_PRODUCT_TO_CART_SUCCESS = ConsoleColor.GREEN +
            "Product with id: %d was successfully added to cart in amount: %d%n" + ConsoleColor.RESET;
    String MSG_ADD_NEW_PRODUCT_TO_CATALOG_SUCCESS = ConsoleColor.GREEN +
            "New product successfully created!" + ConsoleColor.RESET;

    String MSG_ABILITY_CANCEL_OPERATION = ConsoleColor.CYAN +
            String.format("If you want to stop (abort) the operation type '%s' or '%s'",
                    BACK_CMD_FULL_CAST, BACK_CMD_SHORT_CAST) + ConsoleColor.RESET;
    String MSG_ABILITY_SKIP_OPERATION = ConsoleColor.CYAN +
            String.format("If you want to skip (default value will be automatically set) the operation type '%s' or '%s'",
                    SKIP_CMD_FULL_CAST, SKIP_CMD_SHORT_CAST) + ConsoleColor.RESET;

    String MSG_WHEN_APP_RUN = ConsoleColor.CYAN + "Application is start! (shop is opening).\n" +
            "Enter '--help' to see a list of possible commands, or '--stop' to stop the application." + ConsoleColor.RESET;
    String MSG_WHEN_APP_STOP = ConsoleColor.CYAN + "Application is stop! (shop is closing)." + ConsoleColor.RESET;

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
    String MSG_INVALID_NUMERIC_FORMAT_ID = ConsoleColor.RED +
            "Invalid format id (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_ID_FOR_CART = ConsoleColor.CYAN +
            "Please, enter product id which do you want to put to cart:" + ConsoleColor.RESET;
    String MSG_ENTER_PRODUCT_ID_FOR_CATALOG = ConsoleColor.CYAN +
            "Please, enter product id which do you want to add to catalog:" + ConsoleColor.RESET;

    String MSG_PRODUCT_DOES_NOT_EXISTS = ConsoleColor.RED +
            "Product with this id: %d does not exists, try again:%n" + ConsoleColor.RESET;
    String MSG_PRODUCT_ALREADY_EXISTS = ConsoleColor.RED +
            "Product with this id: %d already exists in the catalog, try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_AMOUNT_FOR_CART = ConsoleColor.CYAN +
            "Please, enter amount of this product (amount in stock - %d, amount in your cart - %d):%n" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT = ConsoleColor.RED +
            "Too much value (amount for this product (with id: %d)): %d" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CART = ConsoleColor.RED +
            " (%d (your current choice) + %d (in your cart))" + ConsoleColor.RESET;
    String MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT_FOR_CATALOG = ConsoleColor.RED +
            ", amount products in stock: %d, try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_AMOUNT_PRODUCT_FOR_CATALOG = ConsoleColor.CYAN +
            "Please, enter amount in stock for new product:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_AMOUNT = ConsoleColor.RED +
            "Invalid format amount (cannot be negative or equals zero), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_PRODUCT_AMOUNT = ConsoleColor.RED + "Invalid format amount '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_FRONT_TITLE = ConsoleColor.CYAN +
            "Please, enter a front title for new product:" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_PRICE = ConsoleColor.CYAN + "Please, enter new product price:" + ConsoleColor.RESET;
    String MSG_INVALID_NUMERIC_FORMAT_PRICE = ConsoleColor.RED +
            "Invalid format price (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_PRODUCT_PRICE = ConsoleColor.RED + "Invalid format price '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_PRODUCT_TYPE = ConsoleColor.CYAN +
            "Please, enter type product which do you want to add to catalog:" + ConsoleColor.RESET;
    String MSG_EXISTING_PRODUCT_TYPES = ConsoleColor.CYAN + "Existing types of products: " + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_PRODUCT_TYPE = ConsoleColor.RED +
            "Invalid input '%s', choose from existing product types,try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_BOOK_TITlE = ConsoleColor.CYAN + "Please, enter a title for new book:" + ConsoleColor.RESET;

    String MSG_ENTER_BOOK_AUTHOR = ConsoleColor.CYAN + "Please, enter a author for new book:" + ConsoleColor.RESET;

    String MSG_ENTER_BOOK_LANGUAGE = ConsoleColor.CYAN + "Please, enter a language for new book:" + ConsoleColor.RESET;

    String MSG_ENTER_BOOK_NUMBER_PAGES = ConsoleColor.CYAN + "Please, enter a number of pages for new book:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_NUMBER_PAGES = ConsoleColor.RED +
            "Invalid format number of pages (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_NUMBER_PAGES = ConsoleColor.RED +
            "Invalid format number of pages '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_AUDIOBOOK_SIZE_MB = ConsoleColor.CYAN + "Please, enter a size in MB for new audiobook:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_SIZE_MB = ConsoleColor.RED +
            "Invalid format size [MB] (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_SIZE_MB = ConsoleColor.RED +
            "Invalid format size [MB] '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_AUDIOBOOK_LISTENING_LENGTH = ConsoleColor.CYAN +
            "Please, enter a listening length in minutes for new audiobook:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_LISTENING_LENGTH = ConsoleColor.RED +
            "Invalid format listening length (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_LISTENING_LENGTH = ConsoleColor.RED +
            "Invalid format listening length '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_AUDIOBOOK_NARRATOR = ConsoleColor.CYAN +
            "Please, enter the narrator for new audiobook:" + ConsoleColor.RESET;

    String MSG_ENTER_E_READER_MODEL = ConsoleColor.CYAN +
            "Please, enter the model for new E-Reader:" + ConsoleColor.RESET;

    String MSG_ENTER_E_READER_DISPLAY_SIZE = ConsoleColor.CYAN +
            "Please, enter a display size in inches for new E-Reader:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_DISPLAY_SIZE = ConsoleColor.RED +
            "Invalid format display size [inches] (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_DISPLAY_SIZE = ConsoleColor.RED +
            "Invalid format display size [inches] '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_E_READER_STORAGE_CAPACITY_GB = ConsoleColor.CYAN +
            "Please, enter a storage capacity in GB for new E-Reader:" + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_STORAGE_CAPACITY_GB = ConsoleColor.RED +
            "Invalid format storage capacity [GB] (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_STORAGE_CAPACITY_GB = ConsoleColor.RED +
            "Invalid format storage capacity [GB] '%s', try again:%n" + ConsoleColor.RESET;

    String MSG_ENTER_E_READER_RESOLUTION_PPI = ConsoleColor.CYAN +
            "Please, enter a resolution in PPI for new E-Reader:"  + ConsoleColor.RESET;

    String MSG_INVALID_NUMERIC_FORMAT_RESOLUTION_PPI = ConsoleColor.RED +
            "Invalid format resolution [PPI] (cannot be negative), try again:" + ConsoleColor.RESET;
    String MSG_INVALID_FORMAT_RESOLUTION_PPI = ConsoleColor.RED +
            "Invalid format resolution [PPI] '%s', try again:%n" + ConsoleColor.RESET;
}
