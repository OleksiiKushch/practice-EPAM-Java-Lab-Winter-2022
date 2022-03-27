package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Filters the list of files by their name.
 * @author Oleksii Kushch
 */
public class FilterByName extends FilterLayer {
    private final String name;

    public FilterByName(String name) {
        this.name = name;
    }

    @Override
    public boolean filterOut(File file) {
        if (file.getName().replaceFirst("[.][^.]+$", "").equals(name)) {
            return filterOutNext(file);
        }
        return false;
    }
}
