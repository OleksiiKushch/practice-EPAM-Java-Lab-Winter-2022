package com.epam.task6.create_product.mirror_wrapper_entity;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Commodity;
import com.epam.task4.repository.ProductRepository;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.security.SecureRandom;

public class InitAudiobook extends InitBook {
    private static final String NARRATOR = "Narrator";

    public InitAudiobook(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity initViaConsole(Commodity commodity, ProductDataConsoleScanner productDataConsoleScanner) {
        commodity = super.initViaConsole(commodity, productDataConsoleScanner);
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

    @Override
    public Commodity autoInit(Commodity commodity, ProductRepository productRepository, SecureRandom secureRandom) {
        Audiobook audiobook = (Audiobook) super.autoInit(commodity, productRepository, secureRandom);
        audiobook.setSizeMB(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setListeningLength(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        audiobook.setNarrator(NARRATOR + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        return audiobook;
    }
}
