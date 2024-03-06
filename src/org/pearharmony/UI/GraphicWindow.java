// @Daniel

package org.pearharmony.UI;

import javax.swing.*;

import org.pearharmony.Control.Control;

import java.awt.Image;
import java.nio.file.Path;

public class GraphicWindow extends JFrame {

    Messager messager;
    AddressList addressList;

    public GraphicWindow(Control cont) {
        setTitle("PearHarmony");

        setSize(870, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messager = new Messager(this, cont);
        addressList = new AddressList(messager);
        messager.SetAddressList(addressList);

        add(messager);
        add(addressList);
        add(new JPanel());

        setVisible(true);
    }

    public void ReciveMSG(String sender, String msg) {
        messager.AddMessage(sender, msg);
    }

    public void ReciveMSG(String sender, Image msg, Path path) {
        messager.AddMessage(sender, msg, path);
    }

    public void ReciveSound(String sender, Path path){
        messager.AddSound(sender, path, true);
    }

    public void Update() {
        revalidate();
    }

}
