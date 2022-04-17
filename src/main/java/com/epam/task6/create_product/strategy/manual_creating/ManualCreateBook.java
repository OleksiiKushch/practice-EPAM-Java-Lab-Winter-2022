package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateBook extends ManualCreateCommodity {
    public ManualCreateBook(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        commodity = super.create();
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
}
