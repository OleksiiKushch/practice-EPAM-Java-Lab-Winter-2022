package com.epam.task5.util;

import com.epam.task5.util.filterware.FilterByExtension;
import com.epam.task5.util.filterware.FilterByLastModifiedDate;
import com.epam.task5.util.filterware.FilterByName;
import com.epam.task5.util.filterware.FilterByNone;
import com.epam.task5.util.filterware.FilterBySize;
import com.epam.task5.util.filterware.FilterLayer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * An application that executes search files for a specified set of options in a specified directory and subdirectories.
 * As search parameters, use the file name, extension, file size range, file modification date range.
 * Noted: To form a search filter, use the "Chain of Responsibilities" pattern.<br>
 * The chain of filter links (elements) is as follows:
 * <ul>
 *     <li>by file name ({@link FilterByName})
 *     <li>by file extension ({@link FilterByExtension})
 *     <li>by file size range ({@link FilterBySize})
 *     <li>by file modification date range ({@link FilterByLastModifiedDate})
 * </ul>
 * Through the implementation of the "chain of responsibility" pattern, this allows you to potentially change the order
 * of the filters as you like, spending a minimum amount of effort.
 *
 * @author Oleksii Kushch
 */
public class MyAppFileSearcher {
    /** Root directory pathname. */
    private File rootDirectory;
    /** Start link (element) of the filter chain. */
    private final FilterLayer filterLayer = new FilterByNone();

    public MyAppFileSearcher(String rootDirectoryPath) {
        setRootDirectory(rootDirectoryPath);
    }

    public File getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectoryPath) {
        File rootDirectory = new File(rootDirectoryPath);
        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            this.rootDirectory = rootDirectory;
        }
    }

    /**
     * The main method describing the logic of this class. Performs initialization of filter chain
     * (input required data) and subsequently sequentially filtering the list of files and displaying
     * the result in accordance with a certain format.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        interactSetFlagSearchByName(scanner);
        interactSetFlagSearchByExtension(scanner);
        interactSetFlagSearchBySize(scanner);
        interactSetFlagSearchByUpdateDate(scanner);

        List<File> allFiles = getAllFiles(rootDirectory);
        List<File> result = filterLayer.filterOut(allFiles);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        result.forEach(file -> System.out.println(file.getPath()
                        + " (size: " + file.length() + " bytes) (last modified: "
                        + dateFormat.format(file.lastModified()) + ")"));

        scanner.close();
    }

    /**
     * @param rootDirectory root directory pathname
     * @return list of all files from the specified directory and its subdirectory
     */
    public static List<File> getAllFiles(File rootDirectory) {
        List<File> result = new ArrayList<>();
        recursiveFileCollector(Objects.requireNonNull(rootDirectory.listFiles()), result);
        return result;
    }

    /**
     * Selects only files from array ({@code fileArray}) of file and directory pathnames
     * @param fileArray array of file and directory pathnames
     * @param result list of all files from the {@code fileArray}
     */
    private static void recursiveFileCollector(File[] fileArray, List<File> result) {
        for (File file : fileArray) {
            if (file.isFile()) {
                result.add(file);
            } else if (file.isDirectory()) {
                recursiveFileCollector(Objects.requireNonNull(file.listFiles()), result);
            }
        }
    }

    /**
     * Initializes data (file name) for filtering by file name,
     * and also initializes and binds a link (element) of the filter chain.
     */
    private void interactSetFlagSearchByName(Scanner scanner) {
        System.out.println("искать по имени файла? (0/1)");
        if (Integer.parseInt(scanner.nextLine()) == 1) {
            System.out.println("Введите имя");
            String name = scanner.nextLine();

            filterLayer.linkWith(new FilterByName(name));
        }
    }

    /**
     * Initializes data (file extension) for filtering by file extension,
     * and also initializes and binds a link (element) of the filter chain.
     */
    private void interactSetFlagSearchByExtension(Scanner scanner) {
        System.out.println("искать по расширению файла? (0/1)");
        if (Integer.parseInt(scanner.nextLine()) == 1) {
            System.out.println("Введите расширение");
            String extension = scanner.nextLine();

            filterLayer.linkWith(new FilterByExtension(extension));
        }
    }

    /**
     * Initializes data (range min and max file size in bytes) for filtering by file size range,
     * and also initializes and binds a link (element) of the filter chain.
     */
    private void interactSetFlagSearchBySize(Scanner scanner) {
        System.out.println("искать по диапазону размеров файла? (0/1)");
        if (Integer.parseInt(scanner.nextLine()) == 1) {
            System.out.println("Введите минимальный размер файла (в байтах)");
            long minSize = Long.parseLong(scanner.nextLine());
            System.out.println("Введите максимальний размер файла (в байтах)");
            long maxSize = Long.parseLong(scanner.nextLine());

            filterLayer.linkWith(new FilterBySize(minSize, maxSize));
        }
    }

    /**
     * Initializes data (range start and end dates) for filtering by file modification date range,
     * and also initializes and binds a link (element) of the filter chain.
     */
    private void interactSetFlagSearchByUpdateDate(Scanner scanner) {
        System.out.println("искать по диапазону дат изменения файла? (0/1)");
        if (Integer.parseInt(scanner.nextLine()) == 1) {
            int fromYear = Integer.parseInt(scanner.nextLine());
            int fromMonth = Integer.parseInt(scanner.nextLine());
            int fromDay = Integer.parseInt(scanner.nextLine());
            LocalDateTime fromDate = LocalDateTime.of(fromYear, fromMonth, fromDay, 0, 0, 0);
            int toYear = Integer.parseInt(scanner.nextLine());
            int toMonth = Integer.parseInt(scanner.nextLine());
            int toDay = Integer.parseInt(scanner.nextLine());
            LocalDateTime toDate = LocalDateTime.of(toYear, toMonth, toDay, 0, 0, 0);

            filterLayer.linkWith(new FilterByLastModifiedDate(fromDate, toDate));
        }
    }

    public static void main(String[] args) {
        MyAppFileSearcher fileSearcher =
                new MyAppFileSearcher("src/main/resources/task5_demo_test_data");
        fileSearcher.run();
    }
}
