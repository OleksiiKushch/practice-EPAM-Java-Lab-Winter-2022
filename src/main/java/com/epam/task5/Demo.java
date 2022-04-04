package com.epam.task5;

import com.epam.task5.constants.FilterMessages;
import com.epam.task5.filter.FilterByExtension;
import com.epam.task5.filter.FilterByLastModifiedDate;
import com.epam.task5.filter.FilterByName;
import com.epam.task5.filter.FilterBySize;
import com.epam.task5.filter.FilterLayer;
import com.epam.task5.util.ConsoleScanner;
import com.epam.task5.util.FileSearcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Function;

public class Demo {
    private static final int YES = 1;
    private static final int NO = 0;

    public static void main(String[] args) {
        for (String line : new FileWrapper("src/main/resources/task5_demo_test_data/for_MyViewerTxtFile.txt")) {
            System.out.println(line);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println(FilterMessages.SEARCH_BY_NAME);
        String name = (String) interactGetDataForFilter(scanner, ConsoleScanner::enterName);

        System.out.println(FilterMessages.SEARCH_BY_EXTENSION);
        String extension = (String) interactGetDataForFilter(scanner, ConsoleScanner::enterExtension);

        System.out.println(FilterMessages.SEARCH_BY_SIZE);
        Long minSize = (Long) interactGetDataForFilter(scanner, ConsoleScanner::enterMinSize);
        Long maxSize = null;
        if (minSize != null) {
            maxSize = ConsoleScanner.enterMaxSize(scanner);
        }

        System.out.println(FilterMessages.SEARCH_BY_UPDATE_DATE);
        LocalDateTime fromDate = (LocalDateTime) interactGetDataForFilter(scanner, ConsoleScanner::enterFromDate);
        LocalDateTime toDate = null;
        if (fromDate != null) {
            toDate = ConsoleScanner.enterToDate(scanner);
        }

        FilterLayer filterLayer = new FilterByName(new FilterByExtension(
                        new FilterBySize(new FilterByLastModifiedDate(
                                fromDate, toDate), minSize, maxSize), extension), name);

        FileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data"), filterLayer)
                .forEach(Demo::printFile);

        scanner.close();
    }

    private static void printFile(File file) {
        System.out.printf("%s (size: %d bytes) (last modified: %s)%n", file.getPath(), file.length(),
                new SimpleDateFormat(FilterMessages.DATE_TIME_FORMAT).format(file.lastModified()));
    }

    private static Object interactGetDataForFilter(Scanner scanner, Function<Scanner, Object> enterData) {
        while (true) {
            int isFilterByName = Integer.parseInt(scanner.nextLine());
            if (isFilterByName == YES) {
                return enterData.apply(scanner);
            } else if (isFilterByName == NO) {
                return null;
            } else {
                System.out.println(FilterMessages.INVALID_INPUT);
            }
        }
    }
}
