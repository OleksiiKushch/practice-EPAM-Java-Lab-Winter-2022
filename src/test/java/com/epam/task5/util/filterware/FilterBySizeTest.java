package com.epam.task5.util.filterware;

import com.epam.task5.util.MyAppFileSearcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterBySizeTest {

    @Test
    void filterOut() {
        FilterLayer filter = new FilterBySize(200, 2500);
        List<File> result = filter.filterOut(
                MyAppFileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data")));
        assertEquals("[src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_2\\file_1_1.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_2\\names.txt]", result.toString());
    }
}