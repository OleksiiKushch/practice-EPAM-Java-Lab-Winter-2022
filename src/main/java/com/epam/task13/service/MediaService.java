package com.epam.task13.service;

import com.epam.task11.service.ServiceException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.File;

public interface MediaService {
    boolean saveImage(Part image, String uniqueName) throws ServiceException;
    File getImage(String uniqueName, String pathFolder) ;
    void writeImage(ServletOutputStream out, File imageFile);
    void writeImageWithDefault(ServletOutputStream out, File imageFile, String absolutePathDefaultImage);
}
