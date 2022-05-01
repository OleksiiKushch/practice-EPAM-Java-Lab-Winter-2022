package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;

import java.security.SecureRandom;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateBook extends AutoCreateCommodity {
    public static final String TITLE = "Title";
    public static final String AUTHOR = "Author";
    public static final String LANGUAGE = "Language";

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        Book book = new Book(super.create());

        book.setTitle(TITLE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setAuthor(AUTHOR + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setLanguage(LANGUAGE + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setNumberOfPages(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));

        return book;
    }
}
