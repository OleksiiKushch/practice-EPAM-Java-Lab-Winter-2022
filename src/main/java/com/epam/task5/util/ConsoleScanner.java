package com.epam.task5.util;

import com.epam.task5.constants.FilterMessages;
import com.epam.task5.filter.FilterByExtension;
import com.epam.task5.filter.FilterByLastModifiedDate;
import com.epam.task5.filter.FilterByName;
import com.epam.task5.filter.FilterBySize;
import com.epam.task5.filter.FilterLayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleScanner {
    private static final int YES = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FilterMessages.DATE_TIME_FORMAT);

    public static FilterLayer getFilterByName(Scanner scanner) {
        FilterLayer result = null;
        System.out.println(FilterMessages.SEARCH_BY_NAME);
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            System.out.println(FilterMessages.INPUT_NAME);
            String name = scanner.nextLine();
            result = new FilterByName(name);
        }
        return result;
    }

    public static FilterLayer getFilterByExtension(Scanner scanner) {
        FilterLayer result = null;
        System.out.println(FilterMessages.SEARCH_BY_EXTENSION);
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            System.out.println(FilterMessages.INPUT_EXTENSION);
            String extension = scanner.nextLine();
            result = new FilterByExtension(extension);
        }
        return result;
    }

    public static FilterLayer getFilterSearchBySize(Scanner scanner) {
        FilterLayer result = null;
        System.out.println(FilterMessages.SEARCH_BY_SIZE);
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            System.out.println(FilterMessages.INPUT_MIN_SIZE);
            long minSize = Long.parseLong(scanner.nextLine());
            System.out.println(FilterMessages.INPUT_MAX_SIZE);
            long maxSize = Long.parseLong(scanner.nextLine());
            result = new FilterBySize(minSize, maxSize);
        }
        return result;
    }

    public static FilterLayer getFilterByUpdateDate(Scanner scanner) {
        FilterLayer result = null;
        System.out.println(FilterMessages.SEARCH_BY_UPDATE_DATE);
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            System.out.println(FilterMessages.INPUT_FROM_DATE);
            String strFromDate = scanner.nextLine();
            LocalDateTime fromDate = LocalDateTime.parse(strFromDate, FORMATTER);
            System.out.println(FilterMessages.INPUT_TO_DATE);
            String strToDate = scanner.nextLine();
            LocalDateTime toDate = LocalDateTime.parse(strToDate, FORMATTER);
            result = new FilterByLastModifiedDate(fromDate, toDate);
        }
        return result;
    }
}
