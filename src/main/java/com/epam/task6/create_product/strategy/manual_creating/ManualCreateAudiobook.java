package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateAudiobook extends ManualCreateBook {
    public ManualCreateAudiobook(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        commodity = super.create();
        if (commodity == null) {
            return null;
        }

        Audiobook audiobook = (Audiobook) commodity;

        Integer sizeMB = productDataConsoleScanner.inputSizeMB();
        if (sizeMB == null) {
            return null;
        }
        audiobook.setSizeMB(sizeMB);

        Integer listeningLength = productDataConsoleScanner.inputListeningLength();
        if (listeningLength == null) {
            return null;
        }
        audiobook.setListeningLength(listeningLength);

        String narrator = productDataConsoleScanner.inputNarrator();
        if (narrator == null) {
            return null;
        }
        audiobook.setNarrator(narrator);

        return audiobook;
    }
}
