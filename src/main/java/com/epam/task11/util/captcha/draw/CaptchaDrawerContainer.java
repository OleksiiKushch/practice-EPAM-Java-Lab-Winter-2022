package com.epam.task11.util.captcha.draw;

import com.epam.task11.util.captcha.draw.impl.MyCaptchaDrawerTemplate1;
import com.epam.task11.util.captcha.draw.impl.MyCaptchaDrawerTemplate2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CaptchaDrawerContainer {
    private final List<Supplier<CaptchaDrawer>> container;

    public CaptchaDrawerContainer() {
        container = new ArrayList<>();
        initContainer();
    }

    public CaptchaDrawer getRandom() {
        return container.get(new SecureRandom().nextInt(container.size())).get();
    }

    private void initContainer() {
        container.add(MyCaptchaDrawerTemplate1::new);
        container.add(MyCaptchaDrawerTemplate2::new);
    }
}
