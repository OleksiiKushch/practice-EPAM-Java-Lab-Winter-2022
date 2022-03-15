package com.epam.task5.util.filterware;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<File> filterOut(List<File> listFiles) {
        return filterOutNext(listFiles.stream()
                .filter(file -> file.getName().endsWith(extension))
                .collect(Collectors.toList()));
    }
}
