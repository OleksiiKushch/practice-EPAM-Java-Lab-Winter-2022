package com.epam.task5.util.filterware;

import com.epam.task5.util.MyAppFileSearcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByNameTest {
    @Test
    void filterOut() {
        FilterLayer filter = new FilterByName("file");
        List<File> result = filter.filterOut(
                MyAppFileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data")));
        assertEquals("[src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_1\\file.sql, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_1\\file.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_3\\file.html, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog2\\file.css, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog2\\file.txt]", result.toString());
    }
}