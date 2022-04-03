package com.epam.task4.util;

public class MyValidator {
    public static boolean isNotNegativeOrNotZero(Number number) {
        return number.longValue() > 0;
    }

    public static boolean isNotNegative(Number number) {
        return number.longValue() >= 0;
    }
}
