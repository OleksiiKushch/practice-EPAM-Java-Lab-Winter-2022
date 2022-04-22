package com.epam.task6.util;

import java.math.BigDecimal;

public class NumericValidator {
    private static final int ZERO = 0;

    public static boolean isNotNegativeOrNotZero(Number number) {
        return new BigDecimal(number.toString()).compareTo(BigDecimal.ZERO) > ZERO;
    }

    public static boolean isNotNegative(Number number) {
        return new BigDecimal(number.toString()).compareTo(BigDecimal.ZERO) >= ZERO;
    }
}
