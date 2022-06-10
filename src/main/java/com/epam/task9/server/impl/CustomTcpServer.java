package com.epam.task9.server.impl;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task9.server.Server;
import com.epam.task9.server.controller.ServerCommandContainer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class implementation simple custom TCP/IP socket server.
 *
 * Examples of requests: 'get count' or if there is an argument
 * (part name command and arguments part separation by an equals '='), for example: 'get item=3',
 * if request has several arguments then they are separated by an ampersand '&', for example: 'get receipt=14&22'
 *
 * @author OleksiiKushch
 */
public class CustomTcpServer extends Server {
    public static final int PORT = 3000;

    /** Example: {1st}(get item)={2st}(10). First part is name command, second part is arguments. */
    private static final int NUMBER_OF_PARTS = 2;
    private static final int INDEX_PART_OF_NAME_CMD = 0;
    private static final int INDEX_PART_OF_ARGUMENTS = 1;

    private final ServerCommandContainer serverCommandContainer;

    public CustomTcpServer(ServerCommandContainer serverCommandContainer) {
        this.serverCommandContainer = serverCommandContainer;
    }

    @Override
    public void run() {
        startServer(PORT, serverCommandContainer);
    }

    @Override
    protected void sendResponse(Socket socket, Object object) {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            writer.println(Objects.requireNonNullElse(object, ShopLiterals.MSG_ERROR_404));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected String getCommand(String request) {
        return request.split(ShopLiterals.EQUALS_SING)[INDEX_PART_OF_NAME_CMD];
    }

    @Override
    protected List<String> getArguments(String request) {
        List<String> result = new ArrayList<>();
        String[] parts = request.split(ShopLiterals.EQUALS_SING);
        if (NUMBER_OF_PARTS == parts.length) {
            result = Arrays.asList(parts[INDEX_PART_OF_ARGUMENTS].split(ShopLiterals.AMPERSAND));
        }
        return result;
    }
}
