package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateEReader extends ManualCreateCommodity {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        EReader eReader = new EReader(super.create());

        eReader.setModel(productDataConsoleScanner.inputModel());
        eReader.setDisplaySize(productDataConsoleScanner.inputDisplaySize());
        eReader.setStorageGB(productDataConsoleScanner.inputStorageGB());
        eReader.setResolutionPPI(productDataConsoleScanner.inputResolutionPPI());

        return eReader;
    }
}
