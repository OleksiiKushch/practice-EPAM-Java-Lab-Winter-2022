package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task6.util.ProductDataRandomGenerator;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateBook extends AutoCreateCommodity {
    public AutoCreateBook(ProductDataRandomGenerator productDataRandomGenerator) {
        super(productDataRandomGenerator);
    }

    @Override
    public Commodity create() {
        Book book = new Book(super.create());

        book.setTitle(productDataRandomGenerator.getRandomBookTitle());
        book.setAuthor(productDataRandomGenerator.getRandomBookAuthor());
        book.setLanguage(productDataRandomGenerator.getRandomBookLanguage());
        book.setNumberOfPages(productDataRandomGenerator.getRandomBookNumberOfPages());

        return book;
    }
}
