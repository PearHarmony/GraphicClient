package org.pearharmony;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private DataInputStream in;

    public Handler(Socket _socket) {
        socket = _socket;
    }

    public void run() {
        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            in.readAllBytes();
            //TODO: Output data somewhere
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
