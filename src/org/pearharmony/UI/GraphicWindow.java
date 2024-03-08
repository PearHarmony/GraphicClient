// @Daniel

package org.pearharmony.UI;

import javax.swing.*;

import org.pearharmony.Control.Control;

import java.nio.file.Path;

public class GraphicWindow extends JFrame {

    private Messager messager;
    private AddressList addressList;

    public GraphicWindow(Control cont) {
        setTitle("PearHarmony");

        setSize(870, 760);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // init sub Parts
        messager = new Messager(this, cont);
        addressList = new AddressList(messager);
        messager.setAddressList(addressList);

        add(messager);
        add(addressList);
        add(new JPanel()); // dimmension of last Container are ignored

        setVisible(true);
    }

    public void reciveMSG(String sender, String msg) {
        messager.addMessage(sender, msg);
    }

    public void reciveImage(String sender, Path path) {
        messager.addMessage(sender, path);
    }

    public void reciveSound(String sender, Path path){
        messager.addSound(sender, path, true);
    }
    
}
