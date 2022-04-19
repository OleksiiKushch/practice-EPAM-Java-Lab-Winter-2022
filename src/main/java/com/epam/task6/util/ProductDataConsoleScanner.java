package com.epam.task6.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.create_product.ProductCreatingContainer;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Class for reading data about product (like id or amount) from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 *
 * @author Oleksii Kushch
 * @see PutProductToCartCmd
 */
public class ProductDataConsoleScanner {
    private final ProductService productService;
    private final ProductCreatingContainer productCreatingContainer;

    public ProductDataConsoleScanner(ProductService productService, ProductCreatingContainer productCreatingContainer) {
        this.productService = productService;
        this.productCreatingContainer = productCreatingContainer;
    }

    public Long inputId() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_ID_FOR_CATALOG);
        while (true) {
            String stringId = MainApp.getContext().getScanner().nextLine();
            if (stringId.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    stringId.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                Long id = Long.valueOf(stringId);
                if (!NumericValidator.isNotNegativeOrNotZero(id)) {
                    System.out.println(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_ID);
                } else if (productService.getProductById(id) != null) {
                    System.out.printf(ShopLiterals.MSG_PRODUCT_ALREADY_EXISTS, id);
                } else {
                    return id;
                }
            } catch (NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_ID, stringId);
            }
        }
    }

    public String inputFrontTitle() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_FRONT_TITLE);
        return inputStringParameter();
    }

    public BigDecimal inputPrice() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_PRICE);
        while (true) {
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
            } catch (NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_PRICE, stringPrice);
            }
        }
    }

    public Integer inputAmount() {
        System.out.println(ShopLiterals.MSG_ENTER_AMOUNT_PRODUCT_FOR_CATALOG);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegative,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_AMOUNT,
                ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_AMOUNT);
    }

    public CreateProduct inputCreateProductType() {
        System.out.println(ShopLiterals.MSG_ENTER_PRODUCT_TYPE);
        productCreatingContainer.viewExistingEntities();
        while (true) {
            String entityKey = MainApp.getContext().getScanner().nextLine().toLowerCase().trim();
            if (entityKey.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                    entityKey.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            } else if (productCreatingContainer.isContainEntity(entityKey)) {
                return productCreatingContainer.getEntityByKey(entityKey);
            } else {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_PRODUCT_TYPE, entityKey);
                productCreatingContainer.viewExistingEntities();
            }
        }
    }

    public String inputTitle() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_TITlE);
        return inputStringParameter();
    }

    public String inputAuthor() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_AUTHOR);
        return inputStringParameter();
    }

    public String inputLanguage() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_LANGUAGE);
        return inputStringParameter();
    }

    public Integer inputNumberOfPages() {
        System.out.println(ShopLiterals.MSG_ENTER_BOOK_NUMBER_PAGES);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_NUMBER_PAGES,
                ShopLiterals.MSG_INVALID_FORMAT_NUMBER_PAGES);
    }

    public Integer inputSizeMB() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_SIZE_MB);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_SIZE_MB,
                ShopLiterals.MSG_INVALID_FORMAT_SIZE_MB);
    }

    public Integer inputListeningLength() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_LISTENING_LENGTH);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_LISTENING_LENGTH,
                ShopLiterals.MSG_INVALID_FORMAT_LISTENING_LENGTH);
    }

    public String inputNarrator() {
        System.out.println(ShopLiterals.MSG_ENTER_AUDIOBOOK_NARRATOR);
        return inputStringParameter();
    }

    public String inputModel() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_MODEL);
        return inputStringParameter();
    }

    public Float inputDisplaySize() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_DISPLAY_SIZE);
        return (Float) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Float::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_DISPLAY_SIZE,
                ShopLiterals.MSG_INVALID_FORMAT_DISPLAY_SIZE);
    }

    public Integer inputStorageGB() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_STORAGE_CAPACITY_GB);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_STORAGE_CAPACITY_GB,
                ShopLiterals.MSG_INVALID_FORMAT_STORAGE_CAPACITY_GB);
    }

    public Integer inputResolutionPPI() {
        System.out.println(ShopLiterals.MSG_ENTER_E_READER_RESOLUTION_PPI);
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_RESOLUTION_PPI,
                ShopLiterals.MSG_INVALID_FORMAT_RESOLUTION_PPI);
    }

    private Number inputNumericParameter(Function<Number, Boolean> validator, Function<String, Number> convertor,
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

    private String inputStringParameter() {
        String result = MainApp.getContext().getScanner().nextLine().trim();
        if (result.equals(ShopLiterals.BACK_CMD_FULL_CAST) ||
                result.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
            return null;     // abort the entire operation
        }
        return result;
    }
}
