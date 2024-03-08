// @Veljko
package org.pearharmony.Network;

import org.pearharmony.Control.*;

public class NetworkControler {
    Control control;

    public NetworkControler(Control _control) {
        control = _control;
    }
//method to send data to a peer
    public void send2Peer(String _ip, int _port, byte[] _data) {
        Sender client = new Sender(_ip, _port, _data);
        Thread sender = new Thread(client);
        sender.start();
    }
//method to start listening for incoming messages
    public void startListening(int _port) {
        Listener host = new Listener(_port, control);
        Thread listener = new Thread(host);
        listener.start();
    }
}
