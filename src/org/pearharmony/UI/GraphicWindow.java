package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.Rectangle;

public class GraphicWindow extends JFrame{

    public GraphicWindow(){
        setTitle("PearHarmony");

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(createAdressList());
        add(createMessager());

        setVisible(true);
    }

    private JPanel createAdressList(){
        JPanel addressList = new JPanel();

        addressList.setBounds(new Rectangle(300,700));

        addressList.setBackground(Color.GREEN);

        JTextField name = new JTextField("Addresses");
        name.setEditable(false);

        JScrollPane list = new JScrollPane();

        list.getViewport().add(new JButton("tets"));

        list.add(new JTextField("sdsaf"));

        addressList.add(name);
        addressList.add(list);

        return addressList;
    }

    private JPanel createMessager(){
        JPanel messanger = new JPanel();

        messanger.setBounds(new Rectangle(200,700)); 

        messanger.setBackground(Color.blue);


        return messanger;
    }

}
