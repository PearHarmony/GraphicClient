package org.pearharmony.UI;

import javax.swing.*;


public class GraphicWindow extends JFrame{

    Messager messager;
    AddressList addressList = new AddressList();

    public GraphicWindow(){
        setTitle("PearHarmony");

        setSize(810, 710);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messager = new Messager(this);

        add(messager);
        add(addressList);
        add(new JPanel());

        setVisible(true);
    }

    public void Update(){
        revalidate();
    }

}
