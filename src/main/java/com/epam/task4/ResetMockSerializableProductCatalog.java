package com.epam.task4;

import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.ProductCatalog;
import com.epam.task4.util.UtilProductCatalog;

public class ResetMockSerializableProductCatalog {
    public static void main(String[] args) {
        UtilProductCatalog.safeProductCatalog("product_catalog", InitMockResources.initProductCatalog(new ProductCatalog()));
    }
}
