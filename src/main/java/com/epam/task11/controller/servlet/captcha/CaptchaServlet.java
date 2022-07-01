package com.epam.task11.controller.servlet.captcha;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.draw.CaptchaDrawerContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CaptchaServlet.class);

    private static final String IMG_FORMAT = "jpg";

    private CaptchaDrawerContainer captchaDrawerContainer;

    @Override
    public void init() {
        captchaDrawerContainer = new CaptchaDrawerContainer();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaCodeStorageStrategy captchaCodeStorageStrategy = (CaptchaCodeStorageStrategy) request.getServletContext()
                .getAttribute(ShopLiterals.CAPTCHA_CODE_STORAGE_STRATEGY);

        String code = captchaCodeStorageStrategy.getStoredCode(request);

        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(captchaDrawerContainer.getRandom().drawCaptcha(code), IMG_FORMAT, outputStream);
        outputStream.flush();
    }
}
