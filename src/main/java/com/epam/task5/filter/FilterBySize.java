package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Filters the list of files by their size in bytes.
 * @author Oleksii Kushch
 */
public class FilterBySize extends FilterLayer {
    private Long minSize;
    private Long maxSize;

    public FilterBySize() {

    }

    public FilterBySize(Long minSize, Long maxSize) {
        if (minSize != null && maxSize != null) {
            this.minSize = minSize;
            this.maxSize = maxSize;
        }
    }

    public FilterBySize(FilterLayer next, Long minSize, Long maxSize) {
        this(minSize, maxSize);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return minSize == null || maxSize == null;
    }

    @Override
    public boolean filterOut(File file) {
        if (isNullData()) { // skip this chain link if its data is null
            return filterOutNext(file);
        }
        if (!isNullData() && !(minSize <= file.length() && file.length() <= maxSize)) {
            return false;
        }
        return filterOutNext(file);
    }
}
