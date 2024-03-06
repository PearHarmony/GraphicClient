// @Daniel
// edit: Veljko

package org.pearharmony.Control;

import java.nio.file.Path;
import javax.imageio.ImageIO;

import java.awt.Image;

import org.pearharmony.UI.GraphicWindow;
import org.pearharmony.Network.NetworkControler;
import org.pearharmony.Data.Messages.Message;
import org.pearharmony.Network.Encoder;

public class Control {
    // UI
    GraphicWindow window;

    NetworkControler networkControler;
    Encoder en = new Encoder();

    public Control() {
        window = new GraphicWindow(this);
        networkControler = new NetworkControler(this);
        networkControler.startListening(10000);
    }

    public void Send(Message msg) {
        msg.Send(networkControler, en);
    }

    public void Recive(Message msg) {
        msg.Recive(window);
    }

    @Deprecated
    public void ReciveText(String sender, String msg) {
        window.ReciveMSG(sender + " -> ich", msg);
    }

    @Deprecated
    public void ReciveImage(String sender, Image msg, Path path) {
        window.ReciveMSG(sender + " -> ich", msg, path);
    }

    @Deprecated
    public void ReciveImage(String sender, Path path) {
        try {
            Image msg = ImageIO.read(path.toFile());
            ReciveImage(sender, msg, path);
        } catch (Exception e) {

        }
    }

    @Deprecated
    public void SendText(String addres, String msg) {
        window.ReciveMSG("ich -> " + addres, msg);

        networkControler.send2Peer(addres, 10000, en.text(msg));
    }

    @Deprecated
    public void SendImage(String address, Path path) {
        try {
            Image msg = ImageIO.read(path.toFile());
            window.ReciveMSG("ich -> " + address, msg, path);

            networkControler.send2Peer(address, 10000, en.picture(path));

        } catch (Exception e) {

        }

    }
}
