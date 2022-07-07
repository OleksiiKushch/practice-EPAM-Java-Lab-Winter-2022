package com.epam.task11.util.captcha.draw;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Captcha drawer (render) or just template on which the captcha is drawn.
 *
 * @author Oleksii Kushch
 */
public abstract class CaptchaDrawer {
    public static final int WIDTH = 153;
    public static final int HEIGHT = 58;

    public BufferedImage drawCaptcha(String code) {
        BufferedImage result = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = result.createGraphics();
        drawBackground(g2d);
        drawCode(g2d, code);
        drawInterference(g2d);
        g2d.dispose();
        return result;
    }

    protected abstract void drawBackground(Graphics2D g2d);

    protected abstract void drawCode(Graphics2D g2d, String code);

    protected abstract void drawInterference(Graphics2D g2d);
}
