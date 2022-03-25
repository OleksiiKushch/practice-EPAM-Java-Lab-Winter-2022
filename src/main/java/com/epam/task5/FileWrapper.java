package com.epam.task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

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
        Iterator<String> result = null;
        try {
            return result = new BufferedReader(new FileReader(file)).lines().iterator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
