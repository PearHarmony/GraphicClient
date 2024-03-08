// @Veljko
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

    byte[] dog;//is called dog becouse rng decided to call it so

    public Handler(Socket _socket, Control _control) {
        socket = _socket;
        control = _control;
    }

    public String getIP() {//gets clean ip string
        String string = socket.getInetAddress() + "";
        return string.replace("/", "");
    }

    public void run() {
        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dog = in.readAllBytes();
            switch (de.getType(dog)) {//decides based on type how to decode
                default:
                control.Recive(new TextMessage(getIP(),dog.toString()));
                break;
                case 0x00:
                    control.Recive(
                            new TextMessage(getIP(), de.text(de.cleanData(dog))));//crates new textmessage that is shown
                    break;
                case 0x01:
                    control.Recive(
                            new ImageMessage(getIP(), de.picture(de.cleanData(dog), System.getProperty("user.dir"))));//crates new imagemessage that is shown
                    break;
                case 0x02:
                    control.Recive(
                            new SoundMessage(getIP(), de.sound(de.cleanData(dog), System.getProperty("user.dir"))));//crates new soundmessage that is shown
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
