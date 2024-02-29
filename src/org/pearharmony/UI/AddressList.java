package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddressList extends JPanel implements ActionListener{
    Messager messager;

    private Map<JButton, String> addressList = new HashMap<>();
    JPanel AddrSelList = new JPanel();

    public AddressList(Messager messager) {
        this.messager = messager;
        setBounds(0, 0, 300, 700);

        setBackground(Color.GREEN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton name = new JButton("Addresses");

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        AddAddress("localhost", "localhost");

        JScrollPane list = new JScrollPane(AddrSelList);
        list.setWheelScrollingEnabled(true);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(name);
        add(list);
    }

    public void UpdateAddressList(){
        AddrSelList.removeAll();
        for (JButton button : addressList.keySet()) {
            AddrSelList.add(button);
        }
    }

    public void AddAddress(String name, String address){
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.put(button, address);

        UpdateAddressList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        messager.SetAddres(addressList.get(e.getSource()));
    }
}