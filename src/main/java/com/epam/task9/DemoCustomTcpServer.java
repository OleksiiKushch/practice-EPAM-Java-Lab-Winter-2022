package com.epam.task9;

import com.epam.task9.server.impl.CustomTcpServer;

import java.util.Scanner;

/**
 * @author Oleksii Kushch
 */
public class DemoCustomTcpServer {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine();
            if ("--break".equalsIgnoreCase(request) || "-b".equalsIgnoreCase(request)) {
                break;
            }
            new ImitatorServerClient.Client(request, CustomTcpServer.PORT).start();
        }
    }
}
