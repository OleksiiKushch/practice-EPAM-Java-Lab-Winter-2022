package com.epam.task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A wrapper class for viewing line-by-line a text file  with a loop 'for each'.
 * @author Oleksii Kushch
 */
public class FileWrapper implements Iterable<String> {
    private File file;

    public FileWrapper(String filename) {
        file = new File(filename);
    }

    @Override
    public Iterator<String> iterator() {
        return new FileIterator();
    }

    public class FileIterator implements Iterator<String> {
        private Scanner scanner;

        public FileIterator() {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            return scanner.hasNextLine();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return scanner.nextLine();
        }
    }
}
