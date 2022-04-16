package com.epam.task6.create_product.auto_creating;

import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;
import com.epam.task6.create_product.strategy.AutoProductCreatingStrategy;

import java.security.SecureRandom;

public class AutoCreateEReader extends AutoCreateCommodity {
    private static final String MODEL = "Model";

    public AutoCreateEReader(Commodity commodity) {
        super(commodity);
    }

    @Override
    public Commodity create() {
        SecureRandom secureRandom = new SecureRandom();

        EReader eReader = (EReader) super.create();
        eReader.setModel(MODEL + secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setDisplaySize(secureRandom.nextFloat() * AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM);
        eReader.setStorageGB(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        eReader.setResolutionPPI(secureRandom.nextInt(AutoProductCreatingStrategy.MAX_VALUE_RANDOM_GENERATED_NUM));
        return eReader;
    }
}
