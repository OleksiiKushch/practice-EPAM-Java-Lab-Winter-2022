package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateBook extends ManualCreateCommodity {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Commodity commodity = super.create();
        if (commodity == null) {
            return null;
        }
        Book book = new Book(commodity);

        if (!setField(book::setTitle, productDataConsoleScanner::inputTitle)) {
            return null;
        }
        if (!setField(book::setAuthor, productDataConsoleScanner::inputAuthor)) {
            return null;
        }
        if (!setField(book::setLanguage, productDataConsoleScanner::inputLanguage)) {
            return null;
        }
        if (!setField(book::setNumberOfPages, productDataConsoleScanner::inputNumberOfPages)) {
            return null;
        }

        return book;
    }
}
