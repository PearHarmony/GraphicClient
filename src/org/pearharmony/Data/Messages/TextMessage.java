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
    public void send(NetworkControler controler, Encoder en) {
        controler.send2Peer(address, 10000, en.text(data));
    }

    @Override
    public void recive(GraphicWindow window) {
        window.reciveMSG(address + " -> ich", data);
    }

}
