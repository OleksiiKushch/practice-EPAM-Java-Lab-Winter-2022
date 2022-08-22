package com.epam.task12.controller.servlet;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.ContextListener;
import com.epam.task11.controller.servlet.RegistrationServlet;
import com.epam.task11.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/displayAvatar")
public class DisplayAvatarServlet  extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DisplayAvatarServlet.class);

    private static final String DEFAULT_AVATAR_PATH = "/resources/images/default_avatar.png";
    private static final String DEFAULT_AVATAR = "default_avatar.png";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avatarPath = getLoggedUserAvatarPath(request);
        LOG.debug("Avatar path: " + avatarPath);

        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        if (new File(avatarPath).exists()) {
            writeAvatar(out, avatarPath);
        } else {
            writeAvatar(out, ContextListener.ABSOLUTE_PATH_STORAGE_AVATARS + DEFAULT_AVATAR); // TODO: DEFAULT_AVATAR_PATH with local path
        }
        out.flush();
    }

    private String getLoggedUserAvatarPath(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ShopLiterals.LOGGED_USER);
        return ContextListener.ABSOLUTE_PATH_STORAGE_AVATARS + user.getEmail() + RegistrationServlet.SAVED_AVATAR_EXTENSION;
    }

    private void writeAvatar(ServletOutputStream out, String avatarPath) {
        try (FileInputStream fin = new FileInputStream(avatarPath);
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

}
