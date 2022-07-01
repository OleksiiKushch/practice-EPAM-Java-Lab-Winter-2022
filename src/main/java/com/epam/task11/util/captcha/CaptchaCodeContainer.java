package com.epam.task11.util.captcha;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class CaptchaCodeContainer {
    private Map<Integer, String> container;

    public CaptchaCodeContainer() {
        container = new HashMap<>();
    }

    public void setContainer(Map<Integer, String> container) {
        this.container = container;
    }

    public Map<Integer, String> getContainer() {
        return container;
    }

    public Integer getRandomCaptchaId() {
        int size = container.size();
        Integer[] arrayNumbers = container.keySet().toArray(new Integer[size]);
        return arrayNumbers[new SecureRandom().nextInt(size)];
    }
}
