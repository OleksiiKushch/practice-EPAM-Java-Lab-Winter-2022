package com.epam.task5.filter;

import java.io.File;
import java.util.List;

/**
 * Filter chain base class.
 *
 * @author Oleksii Kushch
 */
public abstract class FilterLayer {
    /**
     * The next link (element) in the chain of filter objects.
     */
    private FilterLayer next;

    /**
     * Helps to build a chain of filter objects.
     * @param next the next link (element) in the chain of filter objects
     */
    public void linkWith(FilterLayer next) {
        if (this.next == null) {
            this.next = next;
        } else {
            this.next.linkWith(next);
        }
    }

    public FilterLayer getNext() {
        return next;
    }

    /**
     * Subclasses implement specific logic (filtering) in this method.
     * @param listFiles list of files to filter
     * @return filtered list of files
     */
    public abstract List<File> filterOut(List<File> listFiles);

    /**
     * Starts filtering on the next object, or ends filtering if we're on the last element in the chain.
     * @param listFiles list of files to filter
     * @return filtered list of files
     */
    protected List<File> filterOutNext(List<File> listFiles) {
        if (next == null) {
            return listFiles;
        }
        return next.filterOut(listFiles);
    }
}
