// @Daniel
// edit: Veljko

package org.pearharmony.Control;

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
}
