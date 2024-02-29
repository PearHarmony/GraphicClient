package org.pearharmony;

public class NetworkControler {
    public NetworkControler() {

    }

    public void send2Peer(String _ip, int _port, byte _type, byte[] _data) {
        Sender client = new Sender(_ip, _port, _type, _data);
        Thread sender = new Thread(client);
        sender.start();
    }

    public void startListening(int _port) {
        Listener host = new Listener(_port);
        Thread listener = new Thread(host);
        listener.start();
    }
}
