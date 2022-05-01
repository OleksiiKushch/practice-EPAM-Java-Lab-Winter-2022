package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * @author Oleksii Kushch
 */
public class ManualCreateBook extends ManualCreateCommodity {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Book book = new Book(super.create());

        book.setTitle(productDataConsoleScanner.inputBookTitle());
        book.setAuthor(productDataConsoleScanner.inputBookAuthor());
        book.setLanguage(productDataConsoleScanner.inputBookLanguage());
        book.setNumberOfPages(productDataConsoleScanner.inputBookNumberOfPages());

        return book;
    }
}
