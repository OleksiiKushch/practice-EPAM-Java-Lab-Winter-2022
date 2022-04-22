package com.epam.task6.test_gzip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestSerialization {
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
}
