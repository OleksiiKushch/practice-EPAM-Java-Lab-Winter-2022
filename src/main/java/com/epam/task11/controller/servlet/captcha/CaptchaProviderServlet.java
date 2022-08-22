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

/**
 * @author Oleksii Kushch
 */
@WebServlet("/captchaProvider")
public class CaptchaProviderServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CaptchaProviderServlet.class);

    private static final String IMG_FORMAT = "jpg";

    /** container with captcha rendering (drawing) templates */
    private CaptchaDrawerContainer captchaDrawerContainer;

    @Override
    public void init() {
        captchaDrawerContainer = new CaptchaDrawerContainer();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = ((CaptchaDataStorageStrategy) request.getServletContext().getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY)).getStoredCode(request);
        log.debug("Stored captcha code: " + code);
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(captchaDrawerContainer.getRandomTemplate().drawCaptcha(code), IMG_FORMAT, outputStream);
        outputStream.flush();
    }
}
