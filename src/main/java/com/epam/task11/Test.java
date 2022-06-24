package com.epam.task11;

import com.epam.task11.util.captcha.draw.CaptchaDrawer;
import com.epam.task11.util.captcha.draw.impl.MyCaptchaDrawerTemplate2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

public class Test {
    private static final int MAX_EXCLUSIVE_NUMBER = 10000;

    public static void main(String[] args) throws IOException {
        String code = String.valueOf(new SecureRandom().nextInt(MAX_EXCLUSIVE_NUMBER));
        CaptchaDrawer captchaDrawer = new MyCaptchaDrawerTemplate2();
        ImageIO.write(captchaDrawer.drawCaptcha(code),
                "jpg", new File("src/main/java/com/epam/task10/myTestCaptcha.jpg"));
    }
}
