package com.epam.task6.create_product.strategy.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;

import java.security.SecureRandom;

/**
 * @author Oleksii Kushch
 */
public class AutoCreateEReader extends AutoCreateCommodity {
    public static final String MODEL = "Model";

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        EReader eReader = new EReader(super.create());

        eReader.setModel(MODEL + secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setDisplaySizeInches(secureRandom.nextFloat() * MAX_VALUE_RANDOM_GENERATED_NUM);
        eReader.setStorageCapacityGB(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setScreenResolutionPPI(secureRandom.nextInt(MAX_VALUE_RANDOM_GENERATED_NUM));

        return eReader;
    }
}
