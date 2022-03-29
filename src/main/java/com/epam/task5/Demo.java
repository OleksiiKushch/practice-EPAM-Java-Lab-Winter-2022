package com.epam.task5;

import com.epam.task5.constants.FilterMessages;
import com.epam.task5.filter.FilterByExtension;
import com.epam.task5.filter.FilterByLastModifiedDate;
import com.epam.task5.filter.FilterByName;
import com.epam.task5.filter.FilterByNone;
import com.epam.task5.filter.FilterBySize;
import com.epam.task5.filter.FilterLayer;
import com.epam.task5.util.ConsoleScanner;
import com.epam.task5.util.FileSearcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class Demo {
    private static final int YES = 1;

    public static void main(String[] args) {
        for (String line : new FileWrapper("src/main/resources/task5_demo_test_data/for_MyViewerTxtFile.txt")) {
            System.out.println(line);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println(FilterMessages.SEARCH_BY_NAME);
        String name = null;
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            name = ConsoleScanner.enterName(scanner);
        }

        System.out.println(FilterMessages.SEARCH_BY_EXTENSION);
        String extension = null;
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            extension = ConsoleScanner.enterExtension(scanner);
        }

        System.out.println(FilterMessages.SEARCH_BY_SIZE);
        Map.Entry<Long, Long> rangeSize = null;
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            rangeSize = ConsoleScanner.enterRangeSize(scanner);
        }

        System.out.println(FilterMessages.SEARCH_BY_UPDATE_DATE);
        Map.Entry<LocalDateTime, LocalDateTime> fromToDate = null;
        if (Integer.parseInt(scanner.nextLine()) == YES) {
            fromToDate = ConsoleScanner.enterRangeUpdateDate(scanner);
        }

        FilterLayer filterByLastModifiedDate = new FilterByLastModifiedDate(fromToDate);
        FilterLayer filterBySize = new FilterBySize(filterByLastModifiedDate, rangeSize);
        FilterLayer filterByExtension = new FilterByExtension(filterBySize, extension);
        FilterLayer filterByName = new FilterByName(filterByExtension, name);

        FilterLayer filterLayer = new FilterByNone(filterByName);

        FileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data"), filterLayer)
                .forEach(Demo::printFile);
        scanner.close();
    }

    public static void printFile(File file) {
        System.out.printf("%s (size: %d bytes) (last modified: %s)%n", file.getPath(), file.length(),
                new SimpleDateFormat(FilterMessages.DATE_TIME_FORMAT).format(file.lastModified()));
    }
}
