package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddressList extends JPanel implements ActionListener{
    Messager messager;

    private Map<JButton, String> addressList = new HashMap<>();
    JPanel AddrSelList = new JPanel();

    JTextField newAddrName = new JTextField(8);
    JTextField newAddrIP = new JTextField(8);
    JButton addNewIndex = new JButton("Add");

    public AddressList(Messager messager) {
        this.messager = messager;
        setBounds(0, 0, 300, 700);

        setBackground(Color.GREEN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        
        

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        AddAddress("localhost", "localhost");

        JTextField name = new JTextField("Addresses");
        name.setMaximumSize(new Dimension(400, 20));

        JScrollPane list = new JScrollPane(AddrSelList);
        list.setWheelScrollingEnabled(true);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel addPanel = new JPanel();
        addPanel.setMaximumSize(new Dimension(400, 20));

        newAddrName.setToolTipText("Name");
        newAddrIP.setToolTipText("IP");

        addNewIndex.addActionListener(this);

        addPanel.add(newAddrName);
        addPanel.add(newAddrIP);
        addPanel.add(addNewIndex);

        add(name);
        add(list);
        add(addPanel);
    }

    public void UpdateAddressList(){
        AddrSelList.removeAll();
        for (JButton button : addressList.keySet()) {
            AddrSelList.add(button);
        }
        revalidate();
    }

    public void AddAddress(String name, String address){
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.put(button, address);

        UpdateAddressList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(addressList.containsKey(e.getSource())){
            messager.SetAddres(addressList.get(e.getSource()));
        }
        else if(e.getSource() == addNewIndex)
        {
            if(newAddrName.getText().length() > 0 && newAddrIP.getText().length() > 0)
            {
                System.out.println("asd");
                AddAddress(newAddrName.getText(), newAddrIP.getText());
                newAddrName.setText("");
                newAddrIP.setText("");
            }
        }
    }
}