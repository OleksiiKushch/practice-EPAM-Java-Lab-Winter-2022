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
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public FilterByLastModifiedDate() {

    }

    public FilterByLastModifiedDate(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate != null && toDate != null) {
            this.fromDate = fromDate;
            this.toDate = toDate;
        }
    }

    public FilterByLastModifiedDate(FilterLayer next, LocalDateTime fromDate, LocalDateTime toDate) {
        this(fromDate, toDate);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return fromDate == null || toDate == null;
    }

    @Override
    public boolean filterOut(File file) {
        if (isNullData()) { // skip this chain link if its data is null
            return filterOutNext(file);
        }
        Date fromDate = Date.from(this.fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(this.toDate.atZone(ZoneId.systemDefault()).toInstant());
        if (!(fromDate.getTime() <= file.lastModified() && file.lastModified() <= toDate.getTime())) {
            return false;
        }
        return filterOutNext(file);
    }
}
