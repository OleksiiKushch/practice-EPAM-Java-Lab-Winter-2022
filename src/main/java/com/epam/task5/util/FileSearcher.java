package com.epam.task5.util;

import com.epam.task5.filter.FilterLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Oleksii Kushch
 */
public class FileSearcher {
    /**
     * @param rootDirectory root directory pathname
     * @return list of all files from the specified directory and its subdirectory
     */
    public static List<File> getAllFiles(File rootDirectory, FilterLayer filterLayer) {
        List<File> result = new ArrayList<>();
        recursiveFileCollector(Objects.requireNonNull(rootDirectory.listFiles()), filterLayer, result);
        return result;
    }

    /**
     * Selects only files from array ({@code fileArray}) of file and directory pathnames
     * @param fileArray array of file and directory pathnames
     * @param result list of all files from the {@code fileArray}
     */
    private static void recursiveFileCollector(File[] fileArray, FilterLayer filterLayer, List<File> result) {
        for (File file : fileArray) {
            if (file.isFile() && filterLayer.filterOut(file)) {
                result.add(file);
            } else if (file.isDirectory()) {
                recursiveFileCollector(Objects.requireNonNull(file.listFiles()), filterLayer, result);
            }
        }
    }
}
