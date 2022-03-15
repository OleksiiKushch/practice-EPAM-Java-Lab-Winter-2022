package com.epam.task5.util.filterware;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Specific element of the file list filter chain. Filters the list of files by their size in bytes.
 * @author Oleksii Kushch
 */
public class FilterBySize extends FilterLayer {
    private final long minSize;
    private final long maxSize;

    public FilterBySize(long minSize, long maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public List<File> filterOut(List<File> listFiles) {
        return filterOutNext(listFiles.stream()
                .filter(file -> minSize <= file.length() && file.length() <= maxSize)
                .collect(Collectors.toList()));
    }
}
