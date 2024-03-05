package org.pearharmony.Data.Messages;

import java.awt.Image;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.pearharmony.Network.Encoder;
import org.pearharmony.Network.NetworkControler;
import org.pearharmony.UI.GraphicWindow;

public class imageMessage extends Message{
    private Path path;

    public imageMessage(String addresse, Path path) {
        super(addresse);
        this.path = path;
    }

    @Override
    public void Send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.picture(path));
    }

    @Override
    public void Recive(GraphicWindow window) {
        try {
            Image msg = ImageIO.read(path.toFile());
            window.ReciveMSG("ich -> " + address, msg, path);
        } catch (Exception e) {

        }
    }
    
}
