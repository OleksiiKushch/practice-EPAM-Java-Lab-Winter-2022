package com.epam.task9.server.impl;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task9.server.Server;
import com.epam.task9.server.controller.ServerCommandContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class implementation simple HTTP socket server.
 *
 * Examples of requests: '{@code http://localhost:8080/shop/count}'
 * or if there is an argument (part name command and arguments part separation by a question mark character '?'),
 * for example: '{@code http://localhost:8080/shop/item?get_info=4}'
 * if request has several arguments then they are separated by an ampersand '&', for example:
 * '{@code http://localhost:8080/shop/receipt?from=14&to=22}'.
 *
 * Noted: in arguments part, argument name, and it's value separation by an equals '='.
 * @author Oleksii Kushch
 */
public class HttpServer extends Server {
    private static final Logger log = LogManager.getLogger(HttpServer.class);

    public static final int PORT = 8080;

    private static final String OK_START_LINE = "HTTP/1.1 200 OK";

    private static final String ERROR_START_LINE = "HTTP/1.1 404 NOT FOUND";

    private static final String CRLF = "\r\n";

    private static final String HTTP_SERVER_COMMAND_REGEX = "([^?]+)(?:\\?([\\S\\s]*))? HTTP/1\\.1";
    private static final Pattern HTTP_SERVER_COMMAND_PATTERN = Pattern.compile(HTTP_SERVER_COMMAND_REGEX);

    private static final int CMD_NAME_GROUP = 1;
    private static final int CMD_ARGUMENTS_GROUP = 2;
    private static final int INDEX_OF_VALUE_OF_CONCRETE_ARGUMENT = 1;
    private static final int LENGTH_OF_ONE_ARGUMENT_PAIR = 2;

    private final ServerCommandContainer serverCommandContainer;

    public HttpServer(ServerCommandContainer serverCommandContainer) {
        this.serverCommandContainer = serverCommandContainer;
    }

    @Override
    public void run() {
        startServer(PORT, serverCommandContainer);
    }

    @Override
    protected void sendResponse(Socket socket, Object object) {
        StringBuilder response = new StringBuilder();
        if (object == null) {
            response.append(ERROR_START_LINE + CRLF + CRLF).append(ShopLiterals.MSG_ERROR_404);
        } else {
            response.append(OK_START_LINE + CRLF + CRLF).append(object);
        }
        response.append(CRLF + CRLF);
        try (OutputStream output = socket.getOutputStream()) {
            output.write(response.toString().getBytes());
            output.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected String getCommand(String request) {
        String result = null;
        if (request != null) {
            Matcher matcher = HTTP_SERVER_COMMAND_PATTERN.matcher(request);
            if (matcher.find()) {
                result = matcher.group(CMD_NAME_GROUP);
            }
        }
        return result;
    }

    @Override
    protected List<String> getArguments(String request) {
        return mapArguments(extractArgumentsPartFromRequest(request));
    }

    private String extractArgumentsPartFromRequest(String request) {
        String result = null;
        Matcher matcher = HTTP_SERVER_COMMAND_PATTERN.matcher(request);
        if (matcher.find()) {
            result = matcher.group(CMD_ARGUMENTS_GROUP);
        }
        log.debug("Arguments part: " + result);
        return result;
    }

    private List<String> mapArguments(String argumentsPartOfRequest) {
        if (argumentsPartOfRequest == null) {
            return new ArrayList<>();
        }
        List<String> result = Arrays.asList(argumentsPartOfRequest.split(ShopLiterals.AMPERSAND));
        if (!isValidArgumentPart(result)) {
            return new ArrayList<>();
        }
        result.replaceAll(entry -> entry.split(ShopLiterals.EQUALS_SING)[INDEX_OF_VALUE_OF_CONCRETE_ARGUMENT]);
        return result;
    }

    private boolean isValidArgumentPart(List<String> arguments) {
        for (String argument : arguments) {
            if (argument.split(ShopLiterals.EQUALS_SING).length != LENGTH_OF_ONE_ARGUMENT_PAIR) {
                return false;
            }
        }
        return true;
    }
}
