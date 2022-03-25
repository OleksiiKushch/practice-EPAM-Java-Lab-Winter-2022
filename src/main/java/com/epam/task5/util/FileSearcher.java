package com.epam.task5.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileSearcher {
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
}
