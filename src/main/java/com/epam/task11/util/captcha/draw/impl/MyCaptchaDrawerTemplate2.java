package com.epam.task11.util.captcha.draw.impl;

import com.epam.task11.util.captcha.draw.CaptchaDrawer;

import java.awt.*;
import java.security.SecureRandom;

public class MyCaptchaDrawerTemplate2 extends CaptchaDrawer {
    private static final int MIN_CODE_FONT_SIZE = 30;
    private static final int MAX_CODE_FONT_SIZE = 52;
    private static final int AMOUNT_INTERFERENCE_DOT = 30;
    private static final int SIZE_INTERFERENCE_DOT = 7;

    @Override
    protected void drawBackground(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    protected void drawCode(Graphics2D g2d, String strCode) {
        SecureRandom random = new SecureRandom();
        g2d.setColor(Color.ORANGE);
        int step = WIDTH / (strCode.length());
        int cursorLetter = 0;
        for (char num : strCode.toCharArray()) {
            int fontSize = random.nextInt(MAX_CODE_FONT_SIZE - MIN_CODE_FONT_SIZE) + MIN_CODE_FONT_SIZE;
            g2d.setFont(new Font("", Font.PLAIN, fontSize));
            g2d.drawString(String.valueOf(num),
                    random.nextInt(step / 2) + cursorLetter,
                    random.nextInt(HEIGHT - fontSize) + fontSize);
            cursorLetter += step;
        }
    }

    @Override
    protected void drawInterference(Graphics2D g2d) {
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < AMOUNT_INTERFERENCE_DOT; i++) {
            g2d.fillOval(random.nextInt(WIDTH), random.nextInt(HEIGHT), SIZE_INTERFERENCE_DOT, SIZE_INTERFERENCE_DOT);
        }
    }
}
