package com.epam.task9.server;

import com.epam.task9.server.controller.ServerCommandContainer;

import java.net.Socket;

/**
 * This thread is responsible to handle client connection.
 *
 * @author Oleksii Kushch
 */
public class ManagerThread extends Thread {
    private final Server server;
    private final Socket socket;
    private final ServerCommandContainer serverCommandContainer;

    public ManagerThread(Server server, Socket socket, ServerCommandContainer serverCommandContainer) {
        this.server = server;
        this.socket = socket;
        this.serverCommandContainer = serverCommandContainer;
    }

    @Override
    public void run() {
        server.processRequest(socket, serverCommandContainer);
    }
}