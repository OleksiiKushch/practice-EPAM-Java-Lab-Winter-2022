package com.epam.task8.util.searcher_file_content;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Find longest sequence of repeated bytes in file.
 *
 * @author Oleksii Kushch
 */
public class SearcherFileContent {
    private static final Logger log = LogManager.getLogger(SearcherFileContent.class);

    /** delay between displays of the current count */
    private static final int DELAY = 100;
    /** {@code STEP} * 100% =  each percentage of numbers displays of the current count */
    private static final double STEP = 0.01;
    private static final int HUNDRED_PERCENT = 100;

    private final Object monitor;

    public SearcherFileContent() {
        monitor = new Object();
    }

    public void interactiveSearcherLongestSequenceOfRepeatedBytes() {
        RunnableSearcherLongestSequence searchThread = new RunnableSearcherLongestSequence();
        searchThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please, input path file: ");
            String filename = scanner.nextLine().strip();

            if ("--break".equals(filename) || "-b".equals(filename)) {
                synchronized (monitor) {
                    monitor.notifyAll();
                }
                searchThread.close();
                scanner.close();
                return;
            }

            byte[] bytes = getAllBytes(filename);
            if (bytes != null) {
                searchThread.setBytes(bytes);

                synchronized (monitor) {
                    monitor.notifyAll();

                    try {
                        monitor.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }

    private byte[] getAllBytes(String filename) {
        byte[] bytes = null;
        try (InputStream inputStream = new FileInputStream(filename)) {
            bytes = inputStream.readAllBytes();
            log.debug("src array bytes: " + Arrays.toString(bytes));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return bytes;
    }

    private class RunnableSearcherLongestSequence extends Thread {
        private byte[] bytes;
        private boolean isRunning;

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public void close() {
            isRunning = false;
        }

        @Override
        public void run() {
            isRunning = true;
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                while(isRunning) {
                    interactiveSearchLongestSequence(bytes);

                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }

        }
    }

    public void interactiveSearchLongestSequence(byte[] bytes) {
        if (bytes.length == 0) {
            System.out.println("File is empty! Nothing found.");
            return;
        }

        int resultCount = 1;
        int resultFrom = 0;
        int resultTo = 0;
        byte resultByte = bytes[0];

        int currentCount = 1;
        int currentFrom = 0;
        byte currentByte;

        double cursor = 0.0;
        for (int i = 0; i < bytes.length; i++) {
            if (i == (int) (bytes.length * cursor)) {
                viewCurrentCountWithDelay(resultCount, cursor);
                cursor += STEP;
            }

            currentByte = bytes[i];
            if (i != bytes.length - 1 && currentByte == bytes[i + 1]) {
                currentCount++;
                continue;
            }
            if (currentCount >= resultCount) {
                resultCount = currentCount;
                resultByte = bytes[i];
                resultFrom = currentFrom;
                resultTo = i;
            }
            currentCount = 1;
            currentFrom = i + 1;
        }

        System.out.printf("Byte: %d, count: %d [%d; %d]\r%n", resultByte, resultCount, resultFrom, resultTo);
    }

    private void viewCurrentCountWithDelay(int count, double cursor) {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.printf("Current count: %d (%d%%)\r", count, (int) (cursor * HUNDRED_PERCENT));
    }
}
