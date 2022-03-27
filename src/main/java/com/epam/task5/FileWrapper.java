package com.epam.task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        private int cursor;
        private List<String> lines;

        public FileIterator() {
            try {
                lines = new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            return cursor < lines.size();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return lines.get(cursor++);
        }
    }
}
