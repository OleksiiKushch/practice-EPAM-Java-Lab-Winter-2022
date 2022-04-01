package com.epam.task6.gzip;

import com.epam.task4.mockdata.InitMockResources;
import com.epam.task4.model.data_sources.ProductCatalog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TestGZip {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path_test_serialized = "src/main/java/com/epam/task6/gzip/test_serialized";
        String path_test_gzip = "src/main/java/com/epam/task6/gzip/test_gzip";
        ProductCatalog productCatalog = InitMockResources.initProductCatalog(new ProductCatalog());
        testSerialized(path_test_serialized, 1, productCatalog);
        testSerialized(path_test_serialized, 4, productCatalog);
        testSerialized(path_test_serialized, 16, productCatalog);
        testSerialized(path_test_serialized, 64, productCatalog);
        testSerialized(path_test_serialized, 256, productCatalog);
        testSerialized(path_test_serialized, 1024, productCatalog);
        testGzip(path_test_gzip, 1, productCatalog);
        testGzip(path_test_gzip, 4, productCatalog);
        testGzip(path_test_gzip, 16, productCatalog);
        testGzip(path_test_gzip, 64, productCatalog);
        testGzip(path_test_gzip, 256, productCatalog);
        testGzip(path_test_gzip, 1024, productCatalog);

        FileInputStream fis = new FileInputStream(path_test_gzip);
        GZIPInputStream gis = new GZIPInputStream(fis);
        ByteArrayInputStream bos = new ByteArrayInputStream(gis.readAllBytes());
        ObjectInputStream ois = new ObjectInputStream(bos);
        ProductCatalog result = (ProductCatalog) ois.readObject();
        System.out.println(result.getProductCatalog());
    }

    public static void testSerialized(String path, int count, Object object) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (int i = 0; i < count; i++) {
                oos.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Serialized file size in bytes for %d written object: %d bytes%n",
                count, Files.size(Paths.get(path)));
    }

    public static void testGzip(String path, int count, Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos);
             FileOutputStream fos = new FileOutputStream(path);
             GZIPOutputStream gzipOS = new GZIPOutputStream(fos)) {
            for (int i = 0; i < count; i++) {
                oos.writeObject(object);
            }
            byte [] data = bos.toByteArray();
            gzipOS.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Gzip file size in bytes for %d written object: %d bytes%n",
                count, Files.size(Paths.get(path)));
    }
}
