package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Does not filter the list of files,
 * but simply skips to the next filtering link (element), if any.
 * @author Oleksii Kushch
 */
public class FilterByNone extends FilterLayer {
    public FilterByNone() {

    }

    public FilterByNone(FilterLayer next) {
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return false;
    }

    @Override
    public boolean filterOut(File file) {
        return filterOutNext(file);
    }
}
