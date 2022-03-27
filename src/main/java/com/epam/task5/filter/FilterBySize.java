package com.epam.task5.filter;

import java.io.File;

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
    public boolean filterOut(File file) {
        if (minSize <= file.length() && file.length() <= maxSize) {
            return filterOutNext(file);
        }
        return false;
    }
}
