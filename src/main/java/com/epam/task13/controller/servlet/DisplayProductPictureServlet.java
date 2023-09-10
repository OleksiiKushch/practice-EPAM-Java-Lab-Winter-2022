package com.epam.task13.controller.servlet;

import com.epam.task11.controller.ContextListener;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.task11.controller.ContextListener.PATH_DEFAULT_PRODUCT_PICTURE;

@WebServlet("/displayProductPicture")
public class DisplayProductPictureServlet extends HttpServlet implements DisplayImage {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        displayImage(request, response, ContextListener.ABSOLUTE_PATH_STORAGE_PRODUCT_PICTURES, PATH_DEFAULT_PRODUCT_PICTURE);
    }
}
