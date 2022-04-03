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
import java.util.function.Function;

/**
 * Class for reading data about product (like id or amount) from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see PutProductToCartCmd
 * @author Oleksii Kushch
 */
public class ProductDataConsoleScanner {
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
                if (!MyValidator.isNotNegativeOrNotZero(id)) {
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
                    System.out.printf(ShopLiterals.MSG_TOO_MUCH_VALUE_PRODUCT_AMOUNT, id, amount + amountOnCart);
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
                if (!MyValidator.isNotNegativeOrNotZero(id)) {
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
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_FRONT_TITLE);
        return inputStringParameter();
    }

    public static BigDecimal inputPrice() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_PRICE);
        while(true) {
            String stringPrice = MainApp.getContext().getScanner().nextLine().trim();
            if (stringPrice.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringPrice.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                BigDecimal price = new BigDecimal(stringPrice);
                if (!(price.compareTo(BigDecimal.ZERO) >= 0)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_PRICE);
                } else {
                    return price;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_PRICE, stringPrice);
            }
        }
    }

    public static Integer inputAmountForCatalog() {
        System.out.println(ShopLiterals.MSG_ENTER_AMOUNT_PRODUCT_FOR_CATALOG);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegative,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT,
                ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_AMOUNT);
    }

    public static Class<? extends Commodity> inputType() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_TYPE);
        System.out.printf(ShopLiterals.MSG_FORMAT_EXISTING_PRODUCT_TYPES, ShopLiterals.BOOK_LITERAL_TYPE,
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
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_TYPE, stringType,
                        ShopLiterals.BOOK_LITERAL_TYPE, ShopLiterals.AUDIOBOOK_LITERAL_TYPE, ShopLiterals.E_READER_LITERAL_TYPE);
            }
        }
    }

    public static String inputTitle() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_TITlE);
        return inputStringParameter();
    }

    public static String inputAuthor() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_AUTHOR);
        return inputStringParameter();
    }

    public static String inputLanguage() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_LANGUAGE);
        return inputStringParameter();
    }

    public static Integer inputNumberOfPages() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_NUMBER_PAGES);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_NUMBER_PAGES,
                ShopLiterals.MSG_INVALID_FORMAT_NUMBER_PAGES);
    }

    public static Integer inputSizeMB() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_SIZE_MB);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_SIZE_MB,
                ShopLiterals.MSG_INVALID_FORMAT_SIZE_MB);
    }

    public static Integer inputListeningLength() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_LISTENING_LENGTH);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_LISTENING_LENGTH,
                ShopLiterals.MSG_INVALID_FORMAT_LISTENING_LENGTH);
    }

    public static String inputNarrator() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_NARRATOR);
        return inputStringParameter();
    }

    public static String inputModel() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_MODEL);
        return inputStringParameter();
    }

    public static Float inputDisplaySize() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_DISPLAY_SIZE);
        return (Float) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Float::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_DISPLAY_SIZE,
                ShopLiterals.MSG_INVALID_FORMAT_DISPLAY_SIZE);
    }

    public static Integer inputStorageGB() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_STORAGE_CAPACITY_GB);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_STORAGE_CAPACITY_GB,
                ShopLiterals.MSG_INVALID_FORMAT_STORAGE_CAPACITY_GB);
    }

    public static Integer inputResolutionPPI() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_RESOLUTION_PPI);
        return (Integer) inputNumericParameter(
                MyValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_RESOLUTION_PPI,
                ShopLiterals.MSG_INVALID_FORMAT_RESOLUTION_PPI);
    }

    private static Number inputNumericParameter(Function<Number, Boolean> validator, Function<String, Number> convertor,
                                                 String msgInvalidNumericFormat, String msgInvalidFormat) {
        while (true) {
            String stringResult = MainApp.getContext().getScanner().nextLine();
            if (stringResult.equals(ShopLiterals.BACK_CMD_FULL_CAST)
                    || stringResult.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Number result = convertor.apply(stringResult);
                if (!validator.apply(result)) {
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
}
