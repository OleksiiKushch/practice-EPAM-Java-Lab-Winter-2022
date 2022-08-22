package com.epam.task11.tag;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.controller.servlet.captcha.CaptchaDataStorageStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Oleksii Kushch
 */
public class CaptchaProvider extends TagSupport {
    @Override
    public int doStartTag() {
        CaptchaDataStorageStrategy captchaDataStorageStrategy = (CaptchaDataStorageStrategy) pageContext.getServletContext()
                .getAttribute(ShopLiterals.CAPTCHA_DATA_STORAGE_STRATEGY);

        captchaDataStorageStrategy.save((HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse());

        try {
            pageContext.getOut().print(captchaDataStorageStrategy.getHtml((HttpServletRequest) pageContext.getRequest()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
