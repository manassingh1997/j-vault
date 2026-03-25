package com.jvault.server;

import com.jvault.core.DataStore;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JVaultServer {
    private final int port;
    private final DataStore dataStore;

    public JVaultServer(int port, DataStore dataStore) {
        this.port = port;
        this.dataStore = dataStore;
    }

    public void start() {
        // try-with-resources ensures the server socket closes if the app crashes
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("J-Vault Server listening on port " + port + "...");

            while (true) {
                // The thread stops here and waits for a connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Hand the connection to a new thread and start it
                ClientHandler handler = new ClientHandler(clientSocket, dataStore);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Server failed to start:" + e.getMessage());
        }
    }
}
