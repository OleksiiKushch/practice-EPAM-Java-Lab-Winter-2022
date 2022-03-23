package com.epam.task4.util;

/**
 * @author Oleksii Kushch
 */
public final class ConsoleColor {
    // Reset
    public static final String RESET = "\033[0m";       // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m";      // RED
    public static final String GREEN = "\033[0;32m";    // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;36m";     // CYAN

    private ConsoleColor() {
        // hide
    }
}

