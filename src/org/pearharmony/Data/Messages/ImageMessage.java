// @Daniel
// edit: Veljko

package org.pearharmony.Data.Messages;

import java.nio.file.Path;


import org.pearharmony.Network.Encoder;
import org.pearharmony.Network.NetworkControler;
import org.pearharmony.UI.GraphicWindow;

public class ImageMessage extends Message{

    private Path path;

    public ImageMessage(String addresse, Path path) {
        super(addresse);
        this.path = path;
    }

    @Override
    public void Send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.picture(path));
    }

    @Override
    public void Recive(GraphicWindow window) {
        window.ReciveImage("ich -> " + address, path);
    }

}
