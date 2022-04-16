package com.epam.task6.create_product.auto_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;

import java.security.SecureRandom;

public class AutoCreateBook extends AutoCreateCommodity {
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String LANGUAGE = "Language";

    public AutoCreateBook(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        Book book = (Book) super.create();
        book.setTitle(TITLE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setAuthor(AUTHOR + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setLanguage(LANGUAGE + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        book.setNumberOfPages(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        return book;
    }
}
