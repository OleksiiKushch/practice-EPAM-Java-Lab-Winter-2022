package com.epam.task5.filter;

import java.io.File;

/**
 * Specific element of the file list filter chain. Filters the list of files by their name.
 * @author Oleksii Kushch
 */
public class FilterByName extends FilterLayer {
    private String name;

    public FilterByName() {

    }

    public FilterByName(String name) {
        this.name = name;
    }

    public FilterByName(FilterLayer next, String name) {
        this(name);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return name == null;
    }

    @Override
    public boolean filterOut(File file) {
        if (isNullData()) { // skip this chain link if its data is null
            return filterOutNext(file);
        }
        if (!file.getName().replaceFirst("[.][^.]+$", "").equals(name)) {
            return false;
        }
        return filterOutNext(file);
    }
}
