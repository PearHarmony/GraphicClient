package org.pearharmony.Network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import org.pearharmony.Data.Messages.ImageMessage;
import org.pearharmony.Data.Messages.SoundMessage;
import org.pearharmony.Data.Messages.TextMessage;
import org.pearharmony.Control.*;

public class Handler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private Decoder de = new Decoder();
    private Control control;

    byte[] dog;

    public Handler(Socket _socket, Control _control) {
        socket = _socket;
        control = _control;
    }

    public String getIP() {
        String string = socket.getInetAddress() + "";
        return string.replace("/", "");
    }

    public void run() {
        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dog = in.readAllBytes();
            switch (de.getType(dog)) {
                case 0x00:
                default:
                    control.Recive(
                            new TextMessage(getIP(), de.text(de.cleanData(dog))));
                    break;
                case 0x01:
                    control.Recive(
                            new ImageMessage(getIP(), de.picture(de.cleanData(dog), System.getProperty("home.dir"))));
                    break;
                case 0x02:
                    control.Recive(
                            new SoundMessage(getIP(), de.sound(dog, System.getProperty("home.dir"))));
                    break;
            }
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
