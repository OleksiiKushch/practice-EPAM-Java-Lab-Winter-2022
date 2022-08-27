package com.epam.task12.controller.servlet;

import com.epam.task11.controller.ContextListener;
import com.epam.task13.controller.servlet.DisplayImage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/displayUserAvatar")
public class DisplayUserAvatarServlet extends HttpServlet implements DisplayImage {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        displayImage(request, response, ContextListener.ABSOLUTE_PATH_STORAGE_USER_AVATARS);
    }
}
