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

        setSize(870, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // init sub Parts
        messager = new Messager(this, cont);
        addressList = new AddressList(messager);
        messager.SetAddressList(addressList);

        add(messager);
        add(addressList);
        add(new JPanel()); // dimmension of last Container are ignored

        setVisible(true);
    }

    public void ReciveMSG(String sender, String msg) {
        messager.AddMessage(sender, msg);
    }

    public void ReciveImage(String sender, Path path) {
        messager.AddMessage(sender, path);
    }

    public void ReciveSound(String sender, Path path){
        messager.AddSound(sender, path, true);
    }
    
}
