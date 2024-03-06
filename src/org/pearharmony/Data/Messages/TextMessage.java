// @Daniel
// edit: Veljko

package org.pearharmony.Data.Messages;

import org.pearharmony.Network.NetworkControler;
import org.pearharmony.Network.Encoder;
import org.pearharmony.UI.GraphicWindow;

public class TextMessage extends Message {
    private String data;

    public TextMessage(String addresse, String data) {
        super(addresse);
        this.data = data;
    }

    @Override
    public void Send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.text(data));
    }

    @Override
    public void Recive(GraphicWindow window) {
        window.ReciveMSG(address + " -> ich", data);
    }

}
