package com.epam.task6.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.PutProductToCartCmd;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task7.constants.MessageKey;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

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
    private final Scanner scanner;

    private final Map<String, Supplier<Object>> methodContainer;

    public ProductDataConsoleScanner(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
        methodContainer = new HashMap<>();
        initMethodContainer();
    }

    public CreateProduct inputCreateProductType() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER_PRODUCT_TYPE);
        MainApp.printMessage(MessageKey.MSG_KEY_EXISTING_PRODUCT_TYPES);
        MainApp.getContext().getProductCreatingEntityContainer().viewExistingEntities();
        while (true) {
            String entityKey = scanner.nextLine().toLowerCase().strip();
            if (MainApp.getContext().getProductCreatingEntityContainer().isContainEntity(entityKey)) {
                return MainApp.getContext().getProductCreatingEntityContainer().getEntityByKey(entityKey);
            } else {
                MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT_PRODUCT_CREATING_TYPE, entityKey);
                MainApp.printWarning(MessageKey.MSG_KEY_EXISTING_PRODUCT_TYPES);
                MainApp.getContext().getProductCreatingEntityContainer().viewExistingEntities();
            }
        }
    }

    public Long inputProductId() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_ID));
        while (true) {
            String stringId = scanner.nextLine().strip();
            try {
                Long id = Long.valueOf(stringId);
                if (!NumericValidator.isNotNegativeOrNotZero(id)) {
                    MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                            MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_ID));
                } else if (productService.getProductById(id) != null) {
                    MainApp.printWarning(MessageKey.MSG_KEY_PRODUCT_WITH_THIS_ID_ALREADY_EXISTS, id);
                } else {
                    return id;
                }
            } catch (NumberFormatException exception) {
                MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT,
                        MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_ID), stringId);
            }
        }
    }

    public String inputProductFrontTitle() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_FRONT_TITLE));
        return inputStringParameter();
    }

    public BigDecimal inputProductPrice() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_PRICE));
        while (true) {
            String stringPrice = scanner.nextLine().strip();
            try {
                BigDecimal price = new BigDecimal(stringPrice);
                if (!(price.compareTo(BigDecimal.ZERO) >= 0)) {
                    MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE,
                            MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_PRICE));
                } else {
                    return price;
                }
            } catch (NumberFormatException exception) {
                MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT,
                        MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_PRICE), stringPrice);
            }
        }
    }

    public Integer inputProductAmount() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_PRODUCT_AMOUNT));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegative,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE,
                ShopLiterals.KEY_PRODUCT_AMOUNT);
    }

    public String inputBookTitle() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_BOOK_TITLE));
        return inputStringParameter();
    }

    public String inputBookAuthor() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_BOOK_AUTHOR));
        return inputStringParameter();
    }

    public String inputBookLanguage() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_BOOK_LANGUAGE));
        return inputStringParameter();
    }

    public Integer inputBookNumberOfPages() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_BOOK_NUMBER_OF_PAGES));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_BOOK_NUMBER_OF_PAGES);
    }

    public Integer inputAudiobookFileSizeMB() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_AUDIOBOOK_FILE_SIZE_MB));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_AUDIOBOOK_FILE_SIZE_MB);
    }

    public Integer inputAudiobookListeningTimeMinutes() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_AUDIOBOOK_LISTENING_TIME_MINUTES));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_AUDIOBOOK_LISTENING_TIME_MINUTES);
    }

    public String inputAudiobookNarrator() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_AUDIOBOOK_NARRATOR));
        return inputStringParameter();
    }

    public String inputEReaderModel() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_E_READER_MODEL));
        return inputStringParameter();
    }

    public Float inputEReaderDisplaySizeInches() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_E_READER_DISPLAY_SIZE_INCHES));
        return (Float) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Float::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_E_READER_DISPLAY_SIZE_INCHES);
    }

    public Integer inputEReaderStorageCapacityGB() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_E_READER_STORAGE_CAPACITY_GB));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_E_READER_STORAGE_CAPACITY_GB);
    }

    public Integer inputEReaderScreenResolutionPPI() {
        MainApp.printMessage(MessageKey.MSG_KEY_ENTER, MainApp.getContext().getResourceBundle().getString(ShopLiterals.KEY_E_READER_SCREEN_RESOLUTION_PPI));
        return (Integer) inputNumericParameter(
                NumericValidator::isNotNegativeOrNotZero,
                Integer::valueOf,
                MessageKey.MSG_KEY_INVALID_FORMAT_CANNOT_BE_NEGATIVE_OR_EQUALS_ZERO,
                ShopLiterals.KEY_E_READER_SCREEN_RESOLUTION_PPI);
    }

    private Number inputNumericParameter(Function<Number, Boolean> validator, Function<String, Number> convertor,
                                         String msgKeyInvalidNumericFormat, String parameterKey) {
        while (true) {
            String stringResult = scanner.nextLine();
            try {
                Number result = convertor.apply(stringResult);
                if (!validator.apply(result)) {
                    MainApp.printWarning(msgKeyInvalidNumericFormat,
                            MainApp.getContext().getResourceBundle().getString(parameterKey));
                } else {
                    return result;
                }
            } catch (NumberFormatException exception) {
                MainApp.printWarning(MessageKey.MSG_KEY_INVALID_FORMAT,
                        MainApp.getContext().getResourceBundle().getString(parameterKey), stringResult);
            }
        }
    }

    private String inputStringParameter() {
        return scanner.nextLine().strip();
    }

    private void initMethodContainer() {
        methodContainer.put(ShopLiterals.KEY_PRODUCT_ID, this::inputProductId);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_FRONT_TITLE, this::inputProductFrontTitle);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_PRICE, this::inputProductPrice);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_AMOUNT, this::inputProductAmount);

        methodContainer.put(ShopLiterals.KEY_BOOK_TITLE, this::inputBookTitle);
        methodContainer.put(ShopLiterals.KEY_BOOK_AUTHOR, this::inputBookAuthor);
        methodContainer.put(ShopLiterals.KEY_BOOK_LANGUAGE, this::inputBookLanguage);
        methodContainer.put(ShopLiterals.KEY_BOOK_NUMBER_OF_PAGES, this::inputBookNumberOfPages);

        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_FILE_SIZE_MB, this::inputAudiobookFileSizeMB);
        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_LISTENING_TIME_MINUTES, this::inputAudiobookListeningTimeMinutes);
        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_NARRATOR, this::inputAudiobookNarrator);

        methodContainer.put(ShopLiterals.KEY_E_READER_MODEL, this::inputEReaderModel);
        methodContainer.put(ShopLiterals.KEY_E_READER_DISPLAY_SIZE_INCHES, this::inputEReaderDisplaySizeInches);
        methodContainer.put(ShopLiterals.KEY_E_READER_STORAGE_CAPACITY_GB, this::inputEReaderStorageCapacityGB);
        methodContainer.put(ShopLiterals.KEY_E_READER_SCREEN_RESOLUTION_PPI, this::inputEReaderScreenResolutionPPI);
    }

    public Map<String, Supplier<Object>> getMethodContainer() {
        return methodContainer;
    }
}
