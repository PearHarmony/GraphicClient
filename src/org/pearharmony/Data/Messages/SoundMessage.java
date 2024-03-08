// @Daniel
// edit: Veljko

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
    public void send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.sound(path));
    }

    @Override
    public void recive(GraphicWindow window) {
        window.reciveSound(address + " -> ich", path);
    }

}
