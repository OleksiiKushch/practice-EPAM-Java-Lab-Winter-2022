package com.epam.task9;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Class with static class 'Client' that imitation a simple client request for
 * a concrete port for simple TCP/IP socket server.
 *
 * @author Oleksii Kushch
 */
public class ImitatorServerClient {
    private static final Logger log = LogManager.getLogger(ImitatorServerClient.class);

    public static class Client extends Thread {
        private final String request;
        private final int port;

        public Client(String request, int port) {
            this.request = request;
            this.port = port;
        }

        @Override
        public void run() {
            try (Socket socket = new Socket(InetAddress.getLocalHost() /* 127.0.0.1 */, port);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                log.debug("Client send request!");
                writer.println(request);

                System.out.println(reader.readLine());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
