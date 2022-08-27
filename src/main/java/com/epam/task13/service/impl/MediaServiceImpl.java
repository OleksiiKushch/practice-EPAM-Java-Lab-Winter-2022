package com.epam.task13.service.impl;

import com.epam.task11.service.ServiceException;
import com.epam.task13.service.MediaService;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class MediaServiceImpl implements MediaService {
    private static final Logger LOG = LogManager.getLogger(MediaServiceImpl.class);

    private static final String DEFAULT_IMAGE = "default.png";
    private static final int SIZE_EMPTY_PART = 0;
    private static final String HTTP_CONTENT_TYPE_DELIMITER = "/";
    private static final String DOT = ".";

    @Override
    public boolean saveImage(Part image, String uniqueName) throws ServiceException {
        if (partIsEmpty(image)) {
            try {
                image.write(uniqueName + DOT + getExtension(image));
            } catch (IOException exception) {
                LOG.warn(exception.getMessage());
                throw new ServiceException(exception.getMessage(), exception);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public File getImage(String uniqueName, String pathFolder) {
        File[] foundFiles = new File(pathFolder).listFiles((file) -> FilenameUtils.removeExtension(file.getName()).equals(uniqueName));
        if (Objects.isNull(foundFiles)) {
            LOG.warn("Files in dir: " + pathFolder + ", by unique name: " + uniqueName + ", not found!");
            return null;
        }
        LOG.debug("Found files in dir: " + pathFolder + ", by unique name: " + uniqueName + " array: " + Arrays.toString(foundFiles));
        Optional<File> result = Arrays.stream(foundFiles).findFirst();
        return result.orElse(null);
    }

    @Override
    public void writeImage(ServletOutputStream out, File imageFile) {
        try (FileInputStream fin = new FileInputStream(imageFile);
             BufferedInputStream bin = new BufferedInputStream(fin);
             BufferedOutputStream bout = new BufferedOutputStream(out)) {
            int ch;
            while((ch = bin.read()) != -1){
                bout.write(ch);
            }
        } catch (IOException exception) {
            LOG.warn(exception.getMessage());
        }
    }

    @Override
    public void writeImageWithDefault(ServletOutputStream out, File imageFile, String absolutePathDefaultImage) {
        if (Objects.nonNull(imageFile)) {
            writeImage(out, imageFile);
        } else {
            writeImage(out, new File(absolutePathDefaultImage + DEFAULT_IMAGE));
        }
    }

    private boolean partIsEmpty(Part part) {
        return Objects.nonNull(part) && part.getSize() > SIZE_EMPTY_PART;
    }

    private String getExtension(Part part) {
        return part.getContentType().split(HTTP_CONTENT_TYPE_DELIMITER)[1];
    }
}
