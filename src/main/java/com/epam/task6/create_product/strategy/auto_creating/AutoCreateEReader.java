package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task6.util.ProductDataRandomGenerator;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateEReader extends AutoCreateCommodity {
    public AutoCreateEReader(ProductDataRandomGenerator productDataRandomGenerator) {
        super(productDataRandomGenerator);
    }

    @Override
    public Commodity create() {
        EReader eReader = new EReader(super.create());

        eReader.setModel(productDataRandomGenerator.getRandomEReaderModel());
        eReader.setDisplaySizeInches(productDataRandomGenerator.getRandomEReaderDisplaySizeInches());
        eReader.setStorageCapacityGB(productDataRandomGenerator.getRandomEReaderStorageCapacityGB());
        eReader.setScreenResolutionPPI(productDataRandomGenerator.getRandomEReaderScreenResolutionPPI());

        return eReader;
    }
}
