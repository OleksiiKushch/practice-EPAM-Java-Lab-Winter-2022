package com.epam.task5.filter;

import java.io.File;

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

    public abstract boolean isNullData();

    /**
     * Subclasses implement specific logic (filtering) in this method.
     */
    public abstract boolean filterOut(File file);

    /**
     * Starts filtering on the next object, or ends filtering if we're on the last element in the chain.
     */
    protected boolean filterOutNext(File file) {
        if (next == null) {
            return true;
        }
        return next.filterOut(file);
    }
}
