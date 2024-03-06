package org.pearharmony.Data.Messages;

import java.nio.file.Path;

import org.pearharmony.Network.Encoder;
import org.pearharmony.Network.NetworkControler;
import org.pearharmony.UI.GraphicWindow;

public class SoundMessage extends Message {
    private Path path;

    public SoundMessage(String addresse, Path path) {
        super(addresse);
        this.path = path;
    }

    @Override
    public void Send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.sound(path));
    }

    @Override
    public void Recive(GraphicWindow window) {
        window.ReciveSound(address + " -> ich", path);
    }

}
