package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Filters the list of files by their extension.
 * @author Oleksii Kushch
 */
public class FilterByExtension extends FilterLayer {
    private final String extension;

    public FilterByExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean filterOut(File file) {
        if (file.getName().endsWith(extension)) {
            return filterOutNext(file);
        }
        return false;
    }
}
