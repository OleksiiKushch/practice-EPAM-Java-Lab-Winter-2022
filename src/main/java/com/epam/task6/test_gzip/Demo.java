package com.epam.task6.test_gzip;

import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.ProductCatalog;

import java.io.IOException;

public class Demo {
    private static final int NUMBER_OF_TEST = 1000;
    private static final String PATH_TEST_FILE = "src/main/java/com/epam/task6/test_gzip/path_test_file";

    public static void main(String[] args) throws IOException {
        ProductCatalog productCatalog = InitMockResources.initProductCatalog(new ProductCatalog());
        for (int i = 1; i <= NUMBER_OF_TEST; i++) {
            TestSerialization.testSerialized(PATH_TEST_FILE, i, productCatalog);
            TestGZip.testGzip(PATH_TEST_FILE, i, productCatalog);
        }
    }
}
