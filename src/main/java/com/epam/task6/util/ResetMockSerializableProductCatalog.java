package com.epam.task6.util;

import com.epam.task4.AppContext;
import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.ProductCatalog;

public class ResetMockSerializableProductCatalog {
    public static void main(String[] args) {
        UtilProductCatalog.safeProductCatalog(AppContext.PATH_PRODUCT_CATALOG,
                InitMockResources.initProductCatalog(new ProductCatalog()));
    }
}
