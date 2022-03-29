package com.epam.task5.filter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Specific element of the file list filter chain. Filters the list of files by their change date range.
 * @author Oleksii Kushch
 */
public class FilterByLastModifiedDate extends FilterLayer {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public FilterByLastModifiedDate() {

    }

    public FilterByLastModifiedDate(Map.Entry<LocalDateTime, LocalDateTime> fromToDate) {
        if (fromToDate != null) {
            this.fromDate = fromToDate.getKey();
            this.toDate = fromToDate.getValue();
        }
    }

    public FilterByLastModifiedDate(FilterLayer next, Map.Entry<LocalDateTime, LocalDateTime> fromToDate) {
        this(fromToDate);
        linkWith(next);
    }

    @Override
    public boolean isNullData() {
        return fromDate == null || toDate == null;
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
