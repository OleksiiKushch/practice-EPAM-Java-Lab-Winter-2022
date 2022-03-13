package com.epam.task4;

import com.epam.task4.controller.CommandHolder;

import java.util.Scanner;

/**
 * Class implementation the console user-interface for business logic "Online store".
 *
 * @author Oleksii Kushch
 */
public class MainApp {
    private boolean isRunning;

    /** initialization application commands */
    private final CommandHolder commandHolder = CommandHolder.getInstance();

    public static final Scanner SCANNER = new Scanner(System.in);

    private MainApp() {
        // hide
    }

    /** main interact method */
    public void run() {
        isRunning = true;
        System.out.println("Enter '--help' to see a list of possible commands, or '--stop' to stop the application.");
        while (isRunning) {
            String command = SCANNER.nextLine().trim();
            if (command.equals("--help")) {
                printHelpCommand();
            } else if (commandHolder.isContain(command)) {
                commandHolder.getCommandByKey(command).execute();
            } else if (command.equals("--stop")) {
                stop();
            } else if (command.isBlank()) {
                // do nothing if the command entered is empty or contains only white space codepoints
                // was mostly added due to accidental pressing 'Enter'
            } else {
                System.out.println("Unsupported command");
            }
        }
        SCANNER.close();
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run();
    }

    /**
     * Print description all commands and base command like '--help' and '--stop'.
     */
    private void printHelpCommand() {
        commandHolder.viewDescriptionAllCommands();
        System.out.println("\n'--help' See a list of possible commands" +
                            "\n'--stop' Stop the application");
    }
}
