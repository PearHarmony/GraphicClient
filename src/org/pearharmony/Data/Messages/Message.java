// @Daniel
// edit: Veljko

package org.pearharmony.Data.Messages;

import org.pearharmony.Network.NetworkControler;
import org.pearharmony.Network.Encoder;
import org.pearharmony.UI.GraphicWindow;

public abstract class Message {
    protected String address;

    public Message(String addresse) {
        this.address = addresse;
    }

    public abstract void Send(NetworkControler controler, Encoder en);

    public abstract void Recive(GraphicWindow window);
}
