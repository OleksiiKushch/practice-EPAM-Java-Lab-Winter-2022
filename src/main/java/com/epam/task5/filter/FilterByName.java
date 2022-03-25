package com.epam.task5.filter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<File> filterOut(List<File> listFiles) {
        return filterOutNext(listFiles.stream()
                .filter(file -> file.getName().replaceFirst("[.][^.]+$", "")
                        .equals(name)).collect(Collectors.toList()));
    }
}
