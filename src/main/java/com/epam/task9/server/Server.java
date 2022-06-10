package com.epam.task9.server;

import com.epam.task9.server.controller.ServerCommand;
import com.epam.task9.server.controller.ServerCommandContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Class implementation base simple TCP/IP socket server that accepts every request
 * from the client and process it's in a new thread.
 * This implementation allows multi-threaded.
 *
 * @author Oleksii Kushch
 */
public abstract class Server extends Thread {
    private static final Logger log = LogManager.getLogger(Server.class);

    protected ServerSocket serverSocket;

    protected void startServer(int port, ServerCommandContainer serverCommandContainer) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            this.serverSocket = serverSocket;
            log.debug(this.getClass().getSimpleName() + " is starting and listening on port: " + serverSocket.getLocalPort());
            while (true) {
                log.debug("Server on port: " + serverSocket.getLocalPort() + " wait new request");
                Socket socket = serverSocket.accept();
                log.debug("New connection (client connected) on port: " + socket.getLocalPort());
                new ManagerThread(this, socket, serverCommandContainer).start(); // handle each socket client in a new thread
            }
        } catch (IOException exception) {
            log.debug(exception.getMessage());
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException exception) {
            log.debug(exception.getMessage());
        }
        log.debug(this.getClass().getSimpleName() + " closed on port: " + serverSocket.getLocalPort());
    }

    protected void processRequest(Socket socket, ServerCommandContainer serverCommandContainer) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String request = bufferedReader.readLine();
            log.debug("Request: " + request);
            String command = getCommand(request);
            log.debug("Command: " + command);

            Object result = null;
            if (serverCommandContainer.isContainCommand(command)) {
                List<String> arguments = getArguments(request);
                log.debug("Arguments: " + arguments);
                ServerCommand serverCommand = serverCommandContainer.getCommandByKey(command);
                serverCommand.execute(arguments);
                result = serverCommand.getResult();
            }
            log.debug("Response: " + result);
            sendResponse(socket, result);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeManagerSocket(socket);
            log.debug("Close connection on port: " + socket.getLocalPort());
        }
    }

    private void closeManagerSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    protected abstract void sendResponse(Socket socket, Object object);

    protected abstract String getCommand(String request);

    protected abstract List<String> getArguments(String request);
}
