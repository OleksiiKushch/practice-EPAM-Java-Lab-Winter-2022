package com.epam.task11.util.captcha.draw.impl;

import com.epam.task11.util.captcha.draw.CaptchaDrawer;

import java.awt.*;

/**
 * @author Oleksii Kushch
 */
public class MyCaptchaDrawerTemplate1 extends CaptchaDrawer {
    private static final Color CODE_COLOR = new Color(95, 75, 139);
    private static final Font CODE_FONT = new Font("", Font.PLAIN, 48);
    private static final int X_ORD_CODE = 22;
    private static final int Y_ORD_CODE = 47;
    private static final int WIDTH_LINE_INTERFERENCE = 3;

    @Override
    protected void drawBackground(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    protected void drawCode(Graphics2D g2d, String strNumber) {
        g2d.setColor(CODE_COLOR);
        g2d.setFont(CODE_FONT);
        g2d.drawString(strNumber, X_ORD_CODE, Y_ORD_CODE);
    }

    @Override
    protected void drawInterference(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(WIDTH_LINE_INTERFERENCE));
        g2d.drawLine(0,0, WIDTH, HEIGHT);
        g2d.drawLine(0, HEIGHT, WIDTH, 0);
        g2d.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
        g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }
}
