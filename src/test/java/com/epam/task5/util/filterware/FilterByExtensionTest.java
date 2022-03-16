package com.epam.task5.util.filterware;

import com.epam.task5.util.MyAppFileSearcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByExtensionTest {
    @Test
    void filterOut() {
        FilterLayer filter = new FilterByExtension("txt");
        List<File> result = filter.filterOut(
                MyAppFileSearcher.getAllFiles(new File("src/main/resources/task5_demo_test_data")));
        assertEquals("[src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_1\\file.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_2\\file_1_1.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_2\\file_1_2.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog1\\subcatalog_1_2\\names.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\catalog2\\file.txt, " +
                "src\\main\\resources\\task5_demo_test_data\\for_MyViewerTxtFile.txt]", result.toString());
    }
}