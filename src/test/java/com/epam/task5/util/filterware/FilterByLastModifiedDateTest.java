package com.epam.task5.util.filterware;

import com.epam.task5.util.MyAppFileSearcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByLastModifiedDateTest {
    @Test
    void filterOut() {
        FilterLayer filter = new FilterByLastModifiedDate(
                LocalDateTime.of(1970, 1, 1, 0, 0, 0),
                LocalDateTime.of(1970, 1, 2, 0, 0, 0));
        List<File> result = filter.filterOut(
                MyAppFileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data")));
        assertEquals("[]", result.toString());
    }
}