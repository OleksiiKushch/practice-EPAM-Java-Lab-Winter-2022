package com.epam.task5.util.filterware;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<File> filterOut(List<File> listFiles) {
        Date fromDate = Date.from(this.fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(this.toDate.atZone(ZoneId.systemDefault()).toInstant());
        return filterOutNext(listFiles.stream()
                .filter(file -> fromDate.getTime() <= file.lastModified()
                        && file.lastModified() <= toDate.getTime())
                .collect(Collectors.toList()));
    }
}
