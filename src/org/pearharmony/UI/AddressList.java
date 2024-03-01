package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddressList extends JPanel implements ActionListener{
    Messager messager;

    private Map<JButton, String> addressList = new HashMap<>();
    JPanel AddrSelList = new JPanel();

    JTextField newAddrName = new JTextField(15);
    JTextField newAddrIP = new JTextField(15);
    JButton addNewIndex = new JButton("Add");    
    JButton removeIndex = new JButton("Remove");

    public AddressList(Messager messager) {
        this.messager = messager;
        setBounds(0, 0, 300, 700);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        AddAddress("localhost", "localhost");

        JTextField name = new JTextField("Addresses");
        name.setMaximumSize(new Dimension(400, 40));
        name.setEditable(false);

        JScrollPane list = new JScrollPane(AddrSelList);
        list.setWheelScrollingEnabled(true);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel addPanel = new JPanel();
        addPanel.setMaximumSize(new Dimension(400, 90));
        addPanel.setPreferredSize(new Dimension(400, 90));
                
        addNewIndex.addActionListener(this);
        removeIndex.addActionListener(this);

        JTextField adr = new JTextField("Addresse:", 8);
        JTextField nam = new JTextField("Name:", 8);

        adr.setEditable(false);
        nam.setEditable(false);

        newAddrName.setToolTipText("Name");
        newAddrIP.setToolTipText("IP");

        addPanel.add(nam);
        addPanel.add(newAddrName);
        addPanel.add(adr);
        addPanel.add(newAddrIP);
        addPanel.add(addNewIndex);
        addPanel.add(removeIndex);

        add(name);
        add(list);
        add(addPanel);
    }

    public void UpdateAddressList(){
        AddrSelList.removeAll();
        for (JButton button : addressList.keySet()) {
            AddrSelList.add(button);
        }
        AddrSelList.repaint();
        revalidate();
    }

    public void AddAddress(String name, String address){
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.put(button, address);

        UpdateAddressList();
    }
    public void RemoveAddress(String name){
        System.out.println("remove: " +name);
        for (int i = 0; i < addressList.size(); i++) {
            if(addressList.values().toArray()[i].equals(name)){
                System.out.println("found");
                addressList.remove(addressList.keySet().toArray()[i]);
                UpdateAddressList();
                return;
            }
        }

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
                AddAddress(newAddrName.getText(), newAddrIP.getText());
                newAddrName.setText("");
                newAddrIP.setText("");
            }
        }else if(e.getSource() == removeIndex){
            RemoveAddress(newAddrName.getText());            
            newAddrName.setText("");
            newAddrIP.setText("");
        }
    }
}