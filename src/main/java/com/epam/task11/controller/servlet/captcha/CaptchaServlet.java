package com.epam.task11.controller.servlet.captcha;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.util.captcha.GeneratorCode;
import com.epam.task11.util.captcha.GeneratorRandomFourDigitNumCode;
import com.epam.task11.util.captcha.draw.ContainerCaptchaDrawer;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final String IMG_FORMAT = "jpg";
    private static final int TIMEOUT = 20;
    private GeneratorCode generatorCode;
    private ContainerCaptchaDrawer containerCaptchaDrawer;

    @Override
    public void init() {
        generatorCode = new GeneratorRandomFourDigitNumCode();
        containerCaptchaDrawer = new ContainerCaptchaDrawer();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = generatorCode.generate();

        HttpSession session = request.getSession();
        request.getSession().setAttribute(ShopLiterals.CAPTCHA_CODE, code);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Future resultFuture = executorService.schedule(() -> session.removeAttribute(ShopLiterals.CAPTCHA_CODE),
                TIMEOUT, TimeUnit.SECONDS);

        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(containerCaptchaDrawer.getRandom().drawCaptcha(code), IMG_FORMAT, outputStream);
        outputStream.flush();
    }
}
