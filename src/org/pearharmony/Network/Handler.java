package org.pearharmony.Network;
import org.pearharmony.UI.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private Decoder de = new Decoder();
    GraphicWindow gw = new GraphicWindow();//TODO: Change this, may open a wnidow every time a message is recived
    byte[] dog;

    public Handler(Socket _socket) {
        socket = _socket;
    }

    public void run() {
        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dog=in.readAllBytes();
            switch (de.getType(dog)) {
                case 1:
                    gw.ReciveMSG(socket.getInetAddress()+"", null,de.picture(de.cleanData(dog), "C:"));
                    break;
            
                default:
                gw.ReciveMSG(socket.getInetAddress()+"", de.text(de.cleanData(dog)));
                    break;
            }
            //TODO: Output data somewhere
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
