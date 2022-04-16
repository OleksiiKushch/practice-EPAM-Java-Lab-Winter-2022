package com.epam.task6.util;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.model.data_sources.ProductCatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UtilProductCatalog {
    public static void safeProductCatalog(String path, ProductCatalog productCatalog) {
        try (FileOutputStream iof = new FileOutputStream(getProductCatalogFile(path));
             ObjectOutputStream oos = new ObjectOutputStream(iof)) {
            oos.writeObject(productCatalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductCatalog loadProductCatalog(String path) {
        File file = getProductCatalogFile(path);
        if (file.isFile() && file.length() == 0) {
            return new ProductCatalog();
        }
        ProductCatalog result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (ProductCatalog) ois.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }

    private static File getProductCatalogFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                System.out.println(ShopLiterals.MSG_ALERT_PRODUCT_CATALOG_FILE_NOT_FOUND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
}
