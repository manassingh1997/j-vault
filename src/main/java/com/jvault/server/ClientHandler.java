package com.jvault.server;

import com.jvault.core.DataStore;
import java.io.*;
import java.net.Socket;

/**
 * Handles an individual client connection in its own thread.
 */

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final DataStore dataStore;

    public ClientHandler(Socket socket, DataStore dataStore){
        this.socket = socket;
        this.dataStore = dataStore;
    }

    @Override
    public void run() {
        // try-with-resources: automatically closes the streams when done
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
            out.println("Connected to J-Vault Server. Send 'QUIT' to exit.");

            String inputLine;
            // readLine() block until the client sends a message
            while ((inputLine = in.readLine()) != null){
                if ("QUIT".equalsIgnoreCase(inputLine.trim())) {
                    out.println("Closing connection. Goodbye");
                    break;
                }
                // parsing commands will be done later;
                out.println("Server received: "+inputLine);
            }
        } catch (IOException e) {
            System.err.println("Client handler exception: " + e.getMessage());
        } finally {
            try {
                socket.close(); // Always clean up network resources
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
