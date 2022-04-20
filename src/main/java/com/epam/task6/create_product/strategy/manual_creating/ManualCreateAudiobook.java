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

        Audiobook audiobook = new Audiobook((Book) super.create());

        audiobook.setSizeMB(productDataConsoleScanner.inputSizeMB());
        audiobook.setListeningLength(productDataConsoleScanner.inputListeningLength());
        audiobook.setNarrator(productDataConsoleScanner.inputNarrator());

        return audiobook;
    }
}
