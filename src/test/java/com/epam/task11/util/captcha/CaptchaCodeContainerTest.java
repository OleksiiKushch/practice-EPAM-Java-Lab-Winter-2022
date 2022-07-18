package com.epam.task11.util.captcha;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CaptchaCodeContainerTest {

    @Test
    void getRandomCaptchaId() {
        CaptchaCodeContainer container = Mockito.spy(new CaptchaCodeContainer());
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1111");
        map.put(4, "4444");
        map.put(19, "1919");
        container.setContainer(map);
        for (int i = 0; i < 10; i++) {
            assertTrue(Arrays.asList(1, 4, 19).contains(container.getRandomCaptchaId()));
        }
    }
}