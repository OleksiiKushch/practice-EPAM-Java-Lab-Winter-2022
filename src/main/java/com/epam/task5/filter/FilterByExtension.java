package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Filters the list of files by their extension.
 * @author Oleksii Kushch
 */
public class FilterByExtension extends FilterLayer {
    private String extension;

    public FilterByExtension() {

    }

    public FilterByExtension(String extension) {
        this.extension = extension;
    }

    public FilterByExtension(FilterLayer next, String extension) {
        this(extension);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return extension == null;
    }

    @Override
    public boolean filterOut(File file) {
        if (isNullData()) { // skip this chain link if its data is null
            return filterOutNext(file);
        }
        if (!file.getName().endsWith(extension)) {
            return false;
        }
        return filterOutNext(file);
    }
}
