package com.epam.task5.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyViewerTxtFileTest {
    private MyViewerTxtFile fileViewer;

    @BeforeEach
    void setUp() {
        fileViewer = new MyViewerTxtFile("src/main/resources/task5_demo_test_data/for_MyViewerTxtFile.txt");
    }

    @Test
    void viewAll() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        fileViewer.viewAll();

        assertEquals("first line\r\n" +
                "second line\r\n" +
                "third line\r\n" +
                "fourth line", outputStreamCaptor.toString().trim());

        // System.setOut(standardOut);
    }
}