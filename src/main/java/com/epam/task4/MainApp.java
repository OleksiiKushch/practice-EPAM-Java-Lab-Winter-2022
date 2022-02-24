package com.epam.task4;

import com.epam.task4.controller.CommandHolder;

import java.util.Scanner;

public class MainApp {
    private boolean isRunning;
    private final CommandHolder commandHolder = CommandHolder.getInstance();

    private MainApp() {

    }

    public void run() {
        isRunning = true;
        System.out.println("Enter '--help' to see a list of possible commands, or '--stop' to stop the application.");
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String command = scanner.nextLine().trim();
            if (command.equals("--help")) {
                printHelpCommand();
            } else if (commandHolder.isContain(command)) {
                commandHolder.getCommandByKey(command).execute();
            } else if (command.equals("--stop")) {
                stop();
            } else if (command.isBlank()) {
            } else {
                System.out.println("Unsupported command");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run();
    }

    private void printHelpCommand() {
        commandHolder.viewDescriptionAllCommands();
        System.out.println("\n'--help' See a list of possible commands" +
                            "\n'--stop' Stop the application");
    }
}
