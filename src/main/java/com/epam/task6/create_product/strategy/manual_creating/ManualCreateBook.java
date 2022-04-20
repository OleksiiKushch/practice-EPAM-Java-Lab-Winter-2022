package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateBook extends ManualCreateCommodity {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Book book = new Book(super.create());

        book.setTitle(productDataConsoleScanner.inputTitle());
        book.setAuthor(productDataConsoleScanner.inputAuthor());
        book.setLanguage(productDataConsoleScanner.inputLanguage());
        book.setNumberOfPages(productDataConsoleScanner.inputNumberOfPages());

        return book;
    }
}
