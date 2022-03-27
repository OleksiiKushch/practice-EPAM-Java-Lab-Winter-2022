package com.epam.task5.filter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Specific element of the file list filter chain. Filters the list of files by their change date range.
 * @author Oleksii Kushch
 */
public class FilterByLastModifiedDate extends FilterLayer {
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public FilterByLastModifiedDate(LocalDateTime fromDate, LocalDateTime toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public boolean filterOut(File file) {
        Date fromDate = Date.from(this.fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(this.toDate.atZone(ZoneId.systemDefault()).toInstant());
        if (fromDate.getTime() <= file.lastModified() && file.lastModified() <= toDate.getTime()) {
            return filterOutNext(file);
        }
        return false;
    }
}
