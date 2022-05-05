package com.epam.task6.util;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.service.ProductService;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductDataRandomGenerator {
    public static final int MAX_VALUE_RANDOM_GENERATED_NUM = 100;

    public static final String FRONT_TITLE = "FrontTitle";

    public static final String TITLE = "Title";
    public static final String AUTHOR = "Author";
    public static final String LANGUAGE = "Language";

    public static final String NARRATOR = "Narrator";

    public static final String MODEL = "Model";

    private final ProductService productService;

    private final SecureRandom secureRandom;

    private final Map<String, Supplier<Object>> methodContainer;

    public ProductDataRandomGenerator(ProductService productService) {
        this.productService = productService;
        secureRandom = new SecureRandom();
        methodContainer = new HashMap<>();
        initMethodContainer();
    }

    public Long getRandomProductId() {
        SecureRandom secureRandom = new SecureRandom();
        long id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productService.getProductById(id) != null) {
            id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        return id;
    }

    public String getRandomProductFrontTitle() {
        return FRONT_TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public BigDecimal getRandomProductPrice() {
        return new BigDecimal(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
    }

    public Integer getRandomProductAmount() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookTitle() {
        return TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookAuthor() {
        return AUTHOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookLanguage() {
        return LANGUAGE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomBookNumberOfPages() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomAudiobookFileSizeMB() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomAudiobookListeningTimeMinutes() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomAudiobookNarrator() {
        return NARRATOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomEReaderModel() {
        return MODEL + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Float getRandomEReaderDisplaySizeInches() {
        return secureRandom.nextFloat() * MAX_VALUE_RANDOM_GENERATED_NUM;
    }

    public Integer getRandomEReaderStorageCapacityGB() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomEReaderScreenResolutionPPI() {
        return secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    private void initMethodContainer() {
        methodContainer.put(ShopLiterals.KEY_PRODUCT_ID, this::getRandomProductId);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_FRONT_TITLE, this::getRandomProductFrontTitle);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_PRICE, this::getRandomProductPrice);
        methodContainer.put(ShopLiterals.KEY_PRODUCT_AMOUNT, this::getRandomProductAmount);

        methodContainer.put(ShopLiterals.KEY_BOOK_TITLE, this::getRandomBookTitle);
        methodContainer.put(ShopLiterals.KEY_BOOK_AUTHOR, this::getRandomBookAuthor);
        methodContainer.put(ShopLiterals.KEY_BOOK_LANGUAGE, this::getRandomBookLanguage);
        methodContainer.put(ShopLiterals.KEY_BOOK_NUMBER_OF_PAGES, this::getRandomBookNumberOfPages);

        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_FILE_SIZE_MB, this::getRandomAudiobookFileSizeMB);
        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_LISTENING_TIME_MINUTES, this::getRandomAudiobookListeningTimeMinutes);
        methodContainer.put(ShopLiterals.KEY_AUDIOBOOK_NARRATOR, this::getRandomAudiobookNarrator);

        methodContainer.put(ShopLiterals.KEY_E_READER_MODEL, this::getRandomEReaderModel);
        methodContainer.put(ShopLiterals.KEY_E_READER_DISPLAY_SIZE_INCHES, this::getRandomEReaderDisplaySizeInches);
        methodContainer.put(ShopLiterals.KEY_E_READER_STORAGE_CAPACITY_GB, this::getRandomEReaderStorageCapacityGB);
        methodContainer.put(ShopLiterals.KEY_E_READER_SCREEN_RESOLUTION_PPI, this::getRandomEReaderScreenResolutionPPI);
    }

    public Map<String, Supplier<Object>> getMethodContainer() {
        return methodContainer;
    }
}
