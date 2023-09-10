package com.epam.task13.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task13.service.MediaService;
import com.epam.task13.service.impl.MediaServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface DisplayImage {

    default void displayImage(HttpServletRequest request, HttpServletResponse response, String sourceFolder, String defaultSourceFolder) throws IOException {
        String imageId = request.getParameter(ShopLiterals.IMAGE_ID);
        LogHolder.LOG.debug("Process image with id (unique name): " + imageId);

        MediaService mediaService = new MediaServiceImpl();
        File imageFile = mediaService.getImage(imageId, sourceFolder);
        LogHolder.LOG.debug("Get image by path: " + imageFile);

        // response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        mediaService.writeImageWithDefault(out, imageFile, sourceFolder, defaultSourceFolder);
        out.flush();
    }
}
final class LogHolder {
    static final Logger LOG = LogManager.getLogger(DisplayImage.class);
}
