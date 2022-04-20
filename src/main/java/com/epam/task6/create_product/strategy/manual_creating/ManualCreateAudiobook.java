package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateAudiobook extends ManualCreateBook {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Book book = (Book) super.create();
        if (book == null) {
            return null;
        }
        Audiobook audiobook = new Audiobook(book);

        if (!setField(audiobook::setSizeMB, productDataConsoleScanner::inputSizeMB)) {
            return null;
        }
        if (!setField(audiobook::setListeningLength, productDataConsoleScanner::inputListeningLength)) {
            return null;
        }
        if (!setField(audiobook::setNarrator, productDataConsoleScanner::inputNarrator)) {
            return null;
        }

        return audiobook;
    }
}
