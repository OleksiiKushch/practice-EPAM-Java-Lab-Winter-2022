package com.epam.task5.util;

import com.epam.task5.constants.FilterMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Contains static methods for interactively getting data from the console for the corresponding
 * filters and returns them (filter).
 *
 * @author Oleksii Kushch
 */
public class ConsoleScanner {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FilterMessages.DATE_TIME_FORMAT);

    public static String enterName(Scanner scanner) {
        System.out.println(FilterMessages.INPUT_NAME);
        return scanner.nextLine();
    }

    public static String enterExtension(Scanner scanner) {
        System.out.println(FilterMessages.INPUT_EXTENSION);
        return scanner.nextLine();
    }

    public static Long enterMinSize(Scanner scanner) {
        return enterSize(FilterMessages.INPUT_MIN_SIZE, scanner);
    }

    public static Long enterMaxSize(Scanner scanner) {
        return enterSize(FilterMessages.INPUT_MAX_SIZE, scanner);
    }

    private static Long enterSize(String message, Scanner scanner) {
        System.out.println(message);
        return Long.parseLong(scanner.nextLine());
    }

    public static LocalDateTime enterFromDate(Scanner scanner) {
        return enterRangeUpdateDate(FilterMessages.INPUT_FROM_DATE, scanner);
    }

    public static LocalDateTime enterToDate(Scanner scanner) {
        return enterRangeUpdateDate(FilterMessages.INPUT_TO_DATE, scanner);
    }

    private static LocalDateTime enterRangeUpdateDate(String message, Scanner scanner) {
        System.out.println(message);
        return LocalDateTime.parse(scanner.nextLine(), FORMATTER);
    }
}
