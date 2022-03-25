package com.epam.task5;

import com.epam.task5.constants.FilterMessages;
import com.epam.task5.filter.FilterByNone;
import com.epam.task5.filter.FilterLayer;
import com.epam.task5.util.ConsoleScanner;
import com.epam.task5.util.FileSearcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        for (String line : new FileWrapper("src/main/resources/task5_demo_test_data/for_MyViewerTxtFile.txt")) {
            System.out.println(line);
        }

        Scanner scanner = new Scanner(System.in);
        FilterLayer filterLayer = new FilterByNone();

        filterLayer.linkWith(ConsoleScanner.getFilterByName(scanner));
        filterLayer.linkWith(ConsoleScanner.getFilterByExtension(scanner));
        filterLayer.linkWith(ConsoleScanner.getFilterSearchBySize(scanner));
        filterLayer.linkWith(ConsoleScanner.getFilterByUpdateDate(scanner));

        filterLayer.filterOut(
                FileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data")))
                .forEach(file -> System.out.printf("%s (size: %d bytes) (last modified: %s)%n", file.getPath(), file.length(),
                        new SimpleDateFormat(FilterMessages.DATE_TIME_FORMAT).format(file.lastModified())));
        scanner.close();
    }
}
