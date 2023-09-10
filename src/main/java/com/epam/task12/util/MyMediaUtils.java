package com.epam.task12.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class MyMediaUtils {

    private static final Logger LOG = LogManager.getLogger(MyMediaUtils.class);

    private static final String ERROR_MESSAGE = "Failed to convert to Base64: ";
    private static final int BUFFER_SIZE = 1024;

    public static String convertToBase64(Part part) {
        String result;
        try (InputStream inputStream = part.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            result = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (IOException exception) {
            LOG.warn(ERROR_MESSAGE + exception.getMessage());
            throw new RuntimeException(ERROR_MESSAGE + exception.getMessage(), exception);
        }
        return result;
    }
}
