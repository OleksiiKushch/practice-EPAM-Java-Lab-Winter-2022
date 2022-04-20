package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task4.MainApp;
import com.epam.task6.util.ProductDataConsoleScanner;

public class ManualCreateEReader extends ManualCreateCommodity {
    @Override
    public Commodity create() {
        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();

        Commodity commodity = super.create();
        if (commodity == null) {
            return null;
        }
        EReader eReader = new EReader(commodity);

        if (!setField(eReader::setModel, productDataConsoleScanner::inputModel)) {
            return null;
        }
        if (!setField(eReader::setDisplaySize, productDataConsoleScanner::inputDisplaySize)) {
            return null;
        }
        if (!setField(eReader::setStorageGB, productDataConsoleScanner::inputStorageGB)) {
            return null;
        }
        if (!setField(eReader::setResolutionPPI, productDataConsoleScanner::inputResolutionPPI)) {
            return null;
        }

        return eReader;
    }
}
