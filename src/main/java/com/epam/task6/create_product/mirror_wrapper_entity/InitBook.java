package com.epam.task6.create_product.mirror_wrapper_entity;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.security.SecureRandom;

public class InitBook extends InitCommodity {
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String LANGUAGE = "Language";

    public InitBook(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity initViaConsole(Commodity commodity, ProductDataConsoleScanner productDataConsoleScanner) {
        commodity = super.initViaConsole(commodity, productDataConsoleScanner);
        if (commodity == null) {
            return null;
        }

        Book book = (Book) commodity;

        String title = productDataConsoleScanner.inputTitle();
        if (title == null) {
            return null;
        }
        book.setTitle(title);

        String author = productDataConsoleScanner.inputAuthor();
        if (author == null) {
            return null;
        }
        book.setAuthor(author);

        String language = productDataConsoleScanner.inputLanguage();
        if (language == null) {
            return null;
        }
        book.setLanguage(language);

        Integer numberOfPages = productDataConsoleScanner.inputNumberOfPages();
        if (numberOfPages == null) {
            return null;
        }
        book.setNumberOfPages(numberOfPages);

        return book;
    }

    @Override
    public Commodity autoInit(Commodity commodity, ProductRepository productRepository, SecureRandom secureRandom) {
        Book book = (Book) super.autoInit(commodity, productRepository, secureRandom);
        book.setTitle(TITLE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setAuthor(AUTHOR + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setLanguage(LANGUAGE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setNumberOfPages(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        return book;
    }
}
