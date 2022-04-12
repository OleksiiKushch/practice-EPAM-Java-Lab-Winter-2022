package com.epam.task6.create_product.mirror_wrapper_entity;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.repository.ProductRepository;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;
import com.epam.task6.util.ProductDataConsoleScanner;

import java.security.SecureRandom;

public class InitEReader extends InitCommodity {
    private static final String MODEL = "Model";

    public InitEReader(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity initViaConsole(Commodity commodity, ProductDataConsoleScanner productDataConsoleScanner) {
        commodity = super.initViaConsole(commodity, productDataConsoleScanner);
        if (commodity == null) {
            return null;
        }

        EReader eReader = (EReader) commodity;

        String model = productDataConsoleScanner.inputModel();
        if (model == null) {
            return null;
        }
        eReader.setModel(model);

        Float displaySize = productDataConsoleScanner.inputDisplaySize();
        if (displaySize == null) {
            return null;
        }
        eReader.setDisplaySize(displaySize);

        Integer storageGB = productDataConsoleScanner.inputStorageGB();
        if (storageGB == null) {
            return null;
        }
        eReader.setStorageGB(storageGB);

        Integer resolutionPPI = productDataConsoleScanner.inputResolutionPPI();
        if (resolutionPPI == null) {
            return null;
        }
        eReader.setResolutionPPI(resolutionPPI);

        return eReader;
    }

    @Override
    public Commodity autoInit(Commodity commodity, ProductRepository productRepository, SecureRandom secureRandom) {
        EReader eReader = (EReader) super.autoInit(commodity, productRepository, secureRandom);
        eReader.setModel(MODEL + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setDisplaySize(secureRandom.nextFloat() * AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        eReader.setStorageGB(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setResolutionPPI(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        return eReader;
    }
}
