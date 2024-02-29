package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddressList extends JPanel implements ActionListener{

    private Map<JButton, String> addressList = new HashMap<>();
    JPanel AddrSelList = new JPanel();

    public AddressList() {
        setBounds(0, 0, 300, 700);

        setBackground(Color.GREEN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton name = new JButton("Addresses");

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

    public void UpdateAddList(){
        for (JButton button : addressList.keySet()) {
            AddrSelList.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addressList.get(e.getSource());
    }
}