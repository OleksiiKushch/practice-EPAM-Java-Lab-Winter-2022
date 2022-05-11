package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * @author Oleksii Kushch
 */
public class ManualCreateAudiobook extends ManualCreateBook {
    public ManualCreateAudiobook(ProductDataConsoleScanner productDataConsoleScanner) {
        super(productDataConsoleScanner);
    }

    @Override
    public Commodity create() {
        Audiobook audiobook = new Audiobook((Book) super.create());

        audiobook.setFileSizeMB(productDataConsoleScanner.inputAudiobookFileSizeMB());
        audiobook.setListeningTimeMinutes(productDataConsoleScanner.inputAudiobookListeningTimeMinutes());
        audiobook.setNarrator(productDataConsoleScanner.inputAudiobookNarrator());

        return audiobook;
    }
}
