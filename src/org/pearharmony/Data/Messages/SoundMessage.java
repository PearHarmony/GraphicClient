package org.pearharmony.Data.Messages;

import java.nio.file.Path;

import org.pearharmony.Network.Encoder;
import org.pearharmony.Network.NetworkControler;
import org.pearharmony.UI.GraphicWindow;

public class SoundMessage extends Message{
    private Path path;

    public SoundMessage(String addresse, Path path) {
        super(addresse);
        this.path = path;
    }

    @Override
    public void Send(NetworkControler controler, Encoder en) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Send'");
    }

    @Override
    public void Recive(GraphicWindow window) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Recive'");
    }

    
}
