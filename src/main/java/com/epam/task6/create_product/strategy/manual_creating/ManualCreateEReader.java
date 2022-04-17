package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateEReader extends ManualCreateCommodity {
    public ManualCreateEReader(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        commodity = super.create();
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
}
