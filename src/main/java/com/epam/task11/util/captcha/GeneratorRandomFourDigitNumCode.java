package com.epam.task11.util.captcha;

import java.security.SecureRandom;

/**
 * Generates a random four-digit numeric code.
 */
public class GeneratorRandomFourDigitNumCode implements GeneratorCode {
    private static final int NUM_DIGITS = 4;

    @Override
    public String generate() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_DIGITS; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
