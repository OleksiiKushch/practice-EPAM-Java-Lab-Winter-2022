package com.epam.task5.filter;

import java.io.File;
import java.util.Map;

/**
 * Specific element of the file list filter chain. Filters the list of files by their size in bytes.
 * @author Oleksii Kushch
 */
public class FilterBySize extends FilterLayer {
    private Long minSize;
    private Long maxSize;

    public FilterBySize() {

    }

    public FilterBySize(Map.Entry<Long, Long> rangeSize) {
        if (rangeSize != null) {
            this.minSize = rangeSize.getKey();
            this.maxSize = rangeSize.getValue();
        }
    }

    public FilterBySize(FilterLayer next, Map.Entry<Long, Long> rangeSize) {
        this(rangeSize);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return minSize == null || maxSize == null;
    }

    @Override
    public boolean filterOut(File file) {
        if (minSize <= file.length() && file.length() <= maxSize) {
            return filterOutNext(file);
        }
        return false;
    }
}
