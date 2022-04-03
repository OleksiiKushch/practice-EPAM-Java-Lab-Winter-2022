package com.epam.task6.create_product.strategy;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.repository.ProductRepository;
import com.epam.task4.util.ProductDataConsoleScanner;

import java.math.BigDecimal;

/**
 * Manual console (with validation logic) creating product
 *
 * @author Oleksii Kushch
 */
public class ManualProductCreatingStrategy implements ProductCreatingStrategy {
    public static final Integer CODE_KEY = 1;

    public static final String FULL_KEY = "--manual";
    public static final String SHORT_KEY = "-m";

    public static final String DESCRIPTION = "Manual mode via console";

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
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setBookParameters(result);
            if (result == null) { return null; }
        } else if (type == Audiobook.class) {
            result = new Audiobook();
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setBookParameters(result);
            if (result == null) { return null; }
            result = setAudiobookParameters(result);
            if (result == null) { return null; }
        } else if (type == EReader.class) {
            result = new EReader();
            result = setBaseParameters(result, productRepository);
            if (result == null) { return null; }
            result = setEReaderParameters(result);
            if (result == null) { return null; }
        }
        return result;
    }

    private Commodity setBaseParameters(Commodity product, ProductRepository productRepository) {
        Long id = ProductDataConsoleScanner.inputIdForCatalog(productRepository);
        if (id == null) { return null; }
        product.setId(id);

        String frontTitle = ProductDataConsoleScanner.inputFrontTitle();
        if (frontTitle == null) { return null; }
        product.setFrontTitle(frontTitle);

        BigDecimal price = ProductDataConsoleScanner.inputPrice();
        if (price == null) { return null; }
        product.setPrice(price);

        Integer amount = ProductDataConsoleScanner.inputAmountForCatalog();
        if (amount == null) { return null; }
        product.setAmount(amount);

        return product;
    }

    private Commodity setBookParameters(Commodity product) {
        Book book = (Book) product;

        String title = ProductDataConsoleScanner.inputTitle();
        if (title == null) { return null; }
        book.setTitle(title);

        String author = ProductDataConsoleScanner.inputAuthor();
        if (author == null) { return null; }
        book.setAuthor(author);

        String language = ProductDataConsoleScanner.inputLanguage();
        if (language == null) { return null; }
        book.setLanguage(language);

        Integer numberOfPages = ProductDataConsoleScanner.inputNumberOfPages();
        if (numberOfPages == null) { return null; }
        book.setNumberOfPages(numberOfPages);

        return book;
    }

    private Commodity setAudiobookParameters(Commodity product) {
        Audiobook audiobook = (Audiobook) product;

        Integer sizeMB = ProductDataConsoleScanner.inputSizeMB();
        if (sizeMB == null) { return null; }
        audiobook.setSizeMB(sizeMB);

        Integer listeningLength = ProductDataConsoleScanner.inputListeningLength();
        if (listeningLength == null) { return null; }
        audiobook.setListeningLength(listeningLength);

        String narrator = ProductDataConsoleScanner.inputNarrator();
        if (narrator == null) { return null; }
        audiobook.setNarrator(narrator);

        return audiobook;
    }

    private Commodity setEReaderParameters(Commodity product) {
        EReader eReader = (EReader) product;

        String model = ProductDataConsoleScanner.inputModel();
        if (model == null) { return null; }
        eReader.setModel(model);

        Float displaySize = ProductDataConsoleScanner.inputDisplaySize();
        if (displaySize == null) { return null; }
        eReader.setDisplaySize(displaySize);

        Integer storageGB = ProductDataConsoleScanner.inputStorageGB();
        if (storageGB == null) { return null; }
        eReader.setStorageGB(storageGB);

        Integer resolutionPPI = ProductDataConsoleScanner.inputResolutionPPI();
        if (resolutionPPI == null) { return null; }
        eReader.setResolutionPPI(resolutionPPI);

        return eReader;
    }
}
