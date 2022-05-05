package com.epam.task6.create_product.strategy.manual_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task6.util.ProductDataConsoleScanner;

/**
 * @author Oleksii Kushch
 */
public class ManualCreateEReader extends ManualCreateCommodity {
    public ManualCreateEReader(ProductDataConsoleScanner productDataConsoleScanner) {
        super(productDataConsoleScanner);
    }

    @Override
    public Commodity create() {
        EReader eReader = new EReader(super.create());

        eReader.setModel(productDataConsoleScanner.inputEReaderModel());
        eReader.setDisplaySizeInches(productDataConsoleScanner.inputEReaderDisplaySizeInches());
        eReader.setStorageCapacityGB(productDataConsoleScanner.inputEReaderStorageCapacityGB());
        eReader.setScreenResolutionPPI(productDataConsoleScanner.inputEReaderScreenResolutionPPI());

        return eReader;
    }
}
