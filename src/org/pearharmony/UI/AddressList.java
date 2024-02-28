package org.pearharmony.UI;

import javax.swing.*;


import java.awt.Color;

public class AddressList extends JPanel{

    public AddressList(){
        setBounds(0,0,300,700);

        setBackground(Color.GREEN);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton name = new JButton("Addresses");
        
        JPanel AddrSelList = new JPanel();

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        AddrSelList.add(new JButton("tets"));
        AddrSelList.add(new JButton("sdsaf"));
        AddrSelList.add(new JButton("tets"));
        AddrSelList.add(new JButton("sdsaf"));

        JScrollPane list = new JScrollPane(AddrSelList);
        list.setWheelScrollingEnabled(true);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(name);
        add(list);
    }
}