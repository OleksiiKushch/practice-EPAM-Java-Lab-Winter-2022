package com.epam.task4.util;

import com.epam.task4.model.data_sources.ProductCatalog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UtilProductCatalog {
    public static void safeProductCatalog(String path, ProductCatalog productCatalog) {
        try (FileOutputStream iof = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(iof)) {
            oos.writeObject(productCatalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductCatalog loadProductCatalog(String path) {
        ProductCatalog result = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (ProductCatalog) ois.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }
}
