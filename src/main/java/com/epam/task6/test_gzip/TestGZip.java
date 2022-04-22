package com.epam.task6.test_gzip;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class TestGZip {
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
