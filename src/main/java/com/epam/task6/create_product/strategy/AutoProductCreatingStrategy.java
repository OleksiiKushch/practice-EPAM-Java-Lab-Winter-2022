package com.epam.task6.create_product.strategy;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.util.ProductDataConsoleScanner;

import java.math.BigDecimal;
import java.security.SecureRandom;

/**
 * Auto random generation creating product
 *
 * @author Oleksii Kushch
 */
public class AutoProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 2;

    public static final String FULL_KEY = "--automatic";
    public static final String SHORT_KEY = "-a";

    public static final String DESCRIPTION = "Automatic mode using random generation";

    private static final String FRONT_TITLE = "FrontTitle";
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String LANGUAGE = "Language";
    private static final String NARRATOR = "Narrator";
    private static final String MODEL = "Model";

    private final SecureRandom secureRandom = new SecureRandom();

    private static final int MAX_VALUE_RANDOM_GENERATED_NUM = 100;

    public static String getFullDescription() {
        return String.format(ShopLiterals.BASE_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    public static String getHelpFullDescription() {
        return String.format(ShopLiterals.HELP_OUTPUT_FORMAT_PRODUCT_CREATING_STRATEGY, CODE_KEY, FULL_KEY, SHORT_KEY, DESCRIPTION);
    }

    @Override
    public Commodity createProduct(ProductRepository productRepository) {
        Commodity result = null;
        Class<? extends Commodity> type = ProductDataConsoleScanner.inputType();
        if (type == Book.class) {
            result = new Book();
            setBaseParameters(result, productRepository);
            result = setBookParameters(result);
        } else if (type == Audiobook.class) {
            result = new Audiobook();
            setBaseParameters(result, productRepository);
            setBookParameters(result);
            result = setAudiobookParameters(result);
        } else if (type == EReader.class) {
            result = new EReader();
            setBaseParameters(result, productRepository);
            result = setEReaderParameters(result);
        }
        return result;
    }

    private void setBaseParameters(Commodity product, ProductRepository productRepository) {
        long id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        while (productRepository.getById(id) != null) {
            id = secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM);
        }
        product.setId(id);
        product.setFrontTitle(FRONT_TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        product.setPrice(new BigDecimal(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM)));
        product.setAmount(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
    }

    private Commodity setBookParameters(Commodity product) {
        Book book = (Book) product;
        book.setTitle(TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setAuthor(AUTHOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setLanguage(LANGUAGE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setNumberOfPages(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        return book;
    }

    private Commodity setAudiobookParameters(Commodity product) {
        Audiobook audiobook = (Audiobook) product;
        audiobook.setSizeMB(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setListeningLength(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setNarrator(NARRATOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        return audiobook;
    }

    private Commodity setEReaderParameters(Commodity product) {
        EReader eReader = (EReader) product;
        eReader.setModel(MODEL + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setDisplaySize(secureRandom.nextFloat() * MAX_VALUE_RANDOM_GENERATED_NUM);
        eReader.setStorageGB(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setResolutionPPI(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        return eReader;
    }
}
