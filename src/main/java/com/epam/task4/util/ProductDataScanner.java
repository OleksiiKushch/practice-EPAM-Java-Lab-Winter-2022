package com.epam.task4.util;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.repository.CartRepository;
import com.epam.task4.repository.ProductRepository;

import java.math.BigDecimal;

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
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_ID_FOR_CART);
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



    public static Long inputIdForCatalog(ProductRepository productRepository) {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_ID_FOR_CATALOG);
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
                } else if (productRepository.getById(id) != null) {
                    System.out.printf(ShopLiterals.MSG_PRODUCT_ALREADY_EXISTS, id);
                } else {
                    return id;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_ID, stringId);
            }
        }
    }

    public static String inputFrontTitle() {
        System.out.println("Please, enter a front title for new product:");
        return inputStringParameter();
    }

    public static BigDecimal inputPrice() {
        System.out.println("Please, enter new product price:");
        while(true) {
            String stringPrice = MainApp.getContext().getScanner().nextLine().trim();
            if (stringPrice.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringPrice.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                BigDecimal price = new BigDecimal(stringPrice);
                if (price.compareTo(BigDecimal.ZERO) >= 0) {
                    System.out.println("Invalid format price (cannot be negative), try again:");
                } else {
                    return price;
                }
            } catch(NumberFormatException exception) {
                System.out.printf("Invalid format price '%s', try again:%n", stringPrice);
            }
        }
    }

    public static Integer inputAmountForStock() {
        System.out.println("Please, enter amount in stock for new product:");
        return inputUnsignedIntParameter(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT_PRODUCT,
                ShopLiterals.MSG_INVALID_FORMAT_AMOUNT_PRODUCT);
    }

    public static Class<? extends Commodity> inputType() {
        System.out.println("Please, enter type product which do you want to add to catalog:");
        System.out.printf("Existing types of products: %s, %s, %S%n", ShopLiterals.BOOK_LITERAL_TYPE,
                ShopLiterals.AUDIOBOOK_LITERAL_TYPE, ShopLiterals.E_READER_LITERAL_TYPE);
        while (true) {
            String stringType = MainApp.getContext().getScanner().nextLine().trim();
            if (stringType.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringType.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            } else if (stringType.equals(ShopLiterals.BOOK_LITERAL_TYPE)) {
                return Book.class;
            } else if (stringType.equals(ShopLiterals.AUDIOBOOK_LITERAL_TYPE)) {
                return Audiobook.class;
            } else if (stringType.equals(ShopLiterals.E_READER_LITERAL_TYPE)) {
                return EReader.class;
            } else {
                System.out.printf("Invalid input '%s', choose from a %s, %s and %s,try again:", stringType,
                        ShopLiterals.BOOK_LITERAL_TYPE, ShopLiterals.AUDIOBOOK_LITERAL_TYPE, ShopLiterals.E_READER_LITERAL_TYPE);
            }
        }
    }

    public static String inputTitle() {
        System.out.println("Please, enter a title for new product (book):");
        return inputStringParameter();
    }

    public static String inputAuthor() {
        System.out.println("Please, enter a title for new product (book):");
        return inputStringParameter();
    }

    public static String inputLanguage() {
        System.out.println("Please, enter a language for new product (book):");
        return inputStringParameter();
    }

    public static Integer inputNumberOfPages() {
        System.out.println("Please, enter a number of pages for new product (book):");
        return inputUnsignedIntParameter(
                "Invalid number of pages (cannot be negative), try again:",
                "Invalid format number of pages '%s', try again:%n");
    }

    public static Integer inputSizeMB() {
        System.out.println("Please, enter a size in MB for new product (audiobook):");
        return inputUnsignedIntParameter(
                "Invalid size (cannot be negative), try again:",
                "Invalid format size '%s', try again:%n");
    }

    public static Integer inputListeningLength() {
        System.out.println("Please, enter a listening length in minutes for new product (audiobook):");
        return inputUnsignedIntParameter(
                "Invalid listening length (cannot be negative), try again:",
                "Invalid format listening length '%s', try again:%n");
    }

    public static String inputNarrator() {
        System.out.println("Please, enter the narrator for new product (book):");
        return inputStringParameter();
    }

    public static String inputModel() {
        System.out.println("Please, enter the model for new product (E-Reader):");
        return inputStringParameter();
    }

    public static Float inputDisplaySize() {
        return null;
    }

    public static Integer inputStorageGB() {
        System.out.println("Please, enter a storage in GB for new product (E-Reader):");
        return inputUnsignedIntParameter(
                "Invalid storage (cannot be negative), try again:",
                "Invalid format storage '%s', try again:%n");
    }

    public static Integer inputResolutionPPI() {
        System.out.println("Please, enter a resolution in PPI for new product (E-Reader):");
        return inputUnsignedIntParameter(
                "Invalid resolution (cannot be negative), try again:",
                "Invalid format resolution '%s', try again:%n");
    }

    private static Integer inputUnsignedIntParameter(String msgInvalidNumericFormat, String msgInvalidFormat) {
        while (true) {
            String stringResult = MainApp.getContext().getScanner().nextLine();
            if (stringResult.equals(ShopLiterals.BACK_CMD_FULL_CAST)
                    || stringResult.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Integer result = Integer.valueOf(stringResult);
                if (result <= 0) {
                    System.out.println(msgInvalidNumericFormat);
                } else {
                    return result;
                }
            } catch (NumberFormatException exception) {
                System.out.printf(msgInvalidFormat, stringResult);
            }
        }
    }

    private static String inputStringParameter() {
        String result = MainApp.getContext().getScanner().nextLine().trim();
        if (result.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                result.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
            return null;     // abort the entire operation
        }
        return result;
    }

    private static boolean isNegativeOrZero(Number number) {
        return number.longValue() <= 0;
    }
}
