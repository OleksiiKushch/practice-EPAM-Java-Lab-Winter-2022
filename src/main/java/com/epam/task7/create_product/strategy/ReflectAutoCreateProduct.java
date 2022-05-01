package com.epam.task7.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.service.ProductService;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateAudiobook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateBook;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateCommodity;
import com.epam.task6.create_product.strategy.auto_creating.AutoCreateEReader;
import com.epam.task7.create_product.ProductField;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Class creating and auto initialization new product with using reflection tech
 * @author Oleksii Kushch
 */
public class ReflectAutoCreateProduct implements CreateProduct {
    private final Map<String, Supplier<Object>> methodContainer;

    private final Commodity commodity;

    public ReflectAutoCreateProduct(Commodity commodity) {
        this.commodity = commodity;
        methodContainer = new HashMap<>();
        initMethodContainer();
    }

    @Override
    public Commodity create() {
        List<Field> fieldList = new ArrayList<>();
        CreateProduct.getProductFields(fieldList, commodity.getClass());

        for (Field field : fieldList) {
            try {
                field.setAccessible(true);
                field.set(commodity, methodContainer.get(field.getAnnotation(ProductField.class).KEY()).get());
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        return commodity;
    }

    public Long getRandomProductId() {
        ProductService productService = MainApp.getContext().getProductService();
        SecureRandom secureRandom = new SecureRandom();
        long id = secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productService.getProductById(id) != null) {
            id = secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        return id;
    }

    public String getRandomProductFrontTitle() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateCommodity.FRONT_TITLE + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public BigDecimal getRandomProductPrice() {
        SecureRandom secureRandom = new SecureRandom();
        return new BigDecimal(secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM));
    }

    public Integer getRandomProductAmount() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookTitle() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateBook.TITLE + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookAuthor() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateBook.AUTHOR + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomBookLanguage() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateBook.LANGUAGE + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomBookNumberOfPages() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomAudiobookFileSizeMB() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomAudiobookListeningTimeMinutes() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomAudiobookNarrator() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateAudiobook.NARRATOR + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public String getRandomEReaderModel() {
        SecureRandom secureRandom = new SecureRandom();
        return AutoCreateEReader.MODEL + secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Float getRandomEReaderDisplaySizeInches() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextFloat() * AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM;
    }

    public Integer getRandomEReaderStorageCapacityGB() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
    }

    public Integer getRandomEReaderScreenResolutionPPI() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(AutoCreateCommodity.MAX_VALUE_RANDOM_GENERATED_NUM);
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
}
