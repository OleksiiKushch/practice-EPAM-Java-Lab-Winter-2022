package com.epam.task5.util;

import com.epam.task5.constants.FilterMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Map;
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

    public static Map.Entry<Long, Long> enterRangeSize(Scanner scanner) {
        System.out.println(FilterMessages.INPUT_MIN_SIZE);
        long minSize = Long.parseLong(scanner.nextLine());
        System.out.println(FilterMessages.INPUT_MAX_SIZE);
        long maxSize = Long.parseLong(scanner.nextLine());
        return new AbstractMap.SimpleEntry<>(minSize, maxSize);
    }

    public static Map.Entry<LocalDateTime, LocalDateTime> enterRangeUpdateDate(Scanner scanner) {
        System.out.println(FilterMessages.INPUT_FROM_DATE);
        String strFromDate = scanner.nextLine();
        LocalDateTime fromDate = LocalDateTime.parse(strFromDate, FORMATTER);
        System.out.println(FilterMessages.INPUT_TO_DATE);
        String strToDate = scanner.nextLine();
        LocalDateTime toDate = LocalDateTime.parse(strToDate, FORMATTER);
        return new AbstractMap.SimpleEntry<>(fromDate, toDate);
    }
}
