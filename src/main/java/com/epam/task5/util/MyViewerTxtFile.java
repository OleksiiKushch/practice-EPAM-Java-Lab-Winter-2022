package com.epam.task5.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

/**
 * A wrapper class for viewing a text file.
 * @author Oleksii Kushch
 */
public class MyViewerTxtFile {
    /** Container store line-by-line parse file. */
    private final List<String> lines = new ArrayList<>();

    /** Message notifying about bumping into the edge of the file. */
    private static final String MSG_EDGE_FILE =
            "**********\nEdge file!\n**********";

    public MyViewerTxtFile(String filename) {
        try {
            new BufferedReader(new FileReader(filename)).lines().forEach(lines::add);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Interactive viewer line-by-line text file. Viewing is allowed both in normal order and in reverse.
     * The output of each next line is proc by the command.<br>
     * In normal order for next line used command '--next' or '-n' or just empty input or input contains
     * only white space codepoints.<br>
     * For reverse order used command '--back' or '-b'.<br>
     * If you want to stop viewering (operation) used command '--stop' or just '-s'.<br>
     * If the conditional file line view cursor rests on the edge of the file, then a message is displayed
     * {@link #MSG_EDGE_FILE}.
     */
    public void runInteractViewerEachLine() {
        ListIterator<String> iterator = lines.listIterator();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        String command;
        String lastCommand = null;
        String lastLine = null;
        while (isRunning) {
            command = scanner.nextLine();
            if (command.equals("--next") || command.equals("-n") || command.isBlank()) {
                if (lastCommand != null && lastLine != null) { // skip first iteration
                    rotationAdjustmentForNext(iterator, lastCommand, lastLine);
                }
                lastLine = iterator.hasNext() ? iterator.next() : MSG_EDGE_FILE;
                lastCommand = command;
            } else if (command.equals("--back") || command.equals("-b")) {
                if (lastCommand != null && lastLine != null) { // skip first iteration
                    rotationAdjustmentForPrevious(iterator, lastCommand, lastLine);
                }
                lastLine = iterator.hasPrevious() ? iterator.previous() : MSG_EDGE_FILE;
                lastCommand = command;
            } else if (command.equals("--stop") || command.equals("-s")) {
                isRunning = false;
            }
            System.out.print(lastLine);
        }
        scanner.close();
    }

    /**
     * Used for logical correct rotation when changing output order direction (normal or reverse order).
     * @param iterator list-iterator for files list
     * @param lastCommand last command used in external method ({@link #runInteractViewerEachLine()}
     * @param lastLine last line output from external method ({@link #runInteractViewerEachLine()},
     *                 especially monitor output of "file edge" message for logical correct rotation
     */
    private void rotationAdjustmentForNext(ListIterator<String> iterator, String lastCommand, String lastLine) {
        if (lastCommand.equals("--next") || lastCommand.equals("-n") || lastCommand.isBlank()) {
            // do nothing
        } else {
            if (iterator.hasNext()) {
                iterator.next();
            }
            if (lastLine.equals(MSG_EDGE_FILE)) { // rollback one step if last operation pointed to edge of file
                iterator.previous();
            }
        }
    }

    /**
     * Used for logical correct rotation when changing output order direction (normal or reverse order).
     * @param iterator list-iterator for files list
     * @param lastCommand last command used in external method ({@link #runInteractViewerEachLine()}
     * @param lastLine last line output from external method ({@link #runInteractViewerEachLine()},
     *                 especially monitor output of "file edge" message for logical correct rotation
     */
    private void rotationAdjustmentForPrevious(ListIterator<String> iterator, String lastCommand, String lastLine) {
        if (lastCommand.equals("--next") || lastCommand.equals("-n") || lastCommand.isBlank()) {
            if (iterator.hasPrevious()) {
                iterator.previous();
            }
            if (lastLine.equals(MSG_EDGE_FILE)) { // rollback one step if last operation pointed to edge of file
                iterator.next();
            }
        } else {
            // do nothing
        }
    }

    /**
     * Displays the contents of a file.
     */
    public void viewAll() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Simple interactive viewer line-by-line text file. The output of each next line is proc
     * by the command (any input from the console).
     */
    public void runSimpleViewerEachLine() {
        Scanner scanner = new Scanner(System.in);
        String command;
        for (String line : lines) {
            command = scanner.nextLine();
            if (!Objects.isNull(command)) {
                System.out.print(line);
            }
        }
        scanner.close();
    }
}
