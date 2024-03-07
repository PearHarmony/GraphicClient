// @Daniel

package org.pearharmony.UI;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressList extends JPanel implements ActionListener {
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

        readAddresses();
    }

    public void readAddresses(){
        try{            
            File text = Paths.get(System.getProperty("user.dir"), "addresses").toFile();
            Scanner reader = new Scanner(text);
            String adrData[];
            while (reader.hasNextLine()) {
                adrData = reader.nextLine().split(" ");
                JButton button = new JButton(adrData[0]);
                button.addActionListener(this);
                addressList.put(button, adrData[1]);
            }           
            reader.close();
            UpdateAddressList();
        } catch (Exception e){

        }
    }

    public void UpdateAddressList() {
        AddrSelList.removeAll();
        for (JButton button : addressList.keySet()) {
            AddrSelList.add(button);
        }
        AddrSelList.repaint();
        revalidate();

        try{            
            File text = Paths.get(System.getProperty("user.dir"), "addresses").toFile();
            FileWriter writer = new FileWriter(text);

            for (var iterable_element : addressList.entrySet()) {
                writer.write(iterable_element.getKey().getText() + " " + iterable_element.getValue() + "\n");
            }            
            writer.close();
        } catch (Exception e){

        }
    }

    public void AddAddress(String name, String address) {
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.put(button, address);

        UpdateAddressList();
    }

    public void RemoveAddress(String name) {
        System.out.println("remove: " + name);
        
        JButton[] button = (JButton[])addressList.keySet().toArray();
        for (int i = 0; i < addressList.size(); i++) {
            if (button[i].getText() != null && button[i].getText().equals(name)) {
                System.out.println("found");
                addressList.remove(button[i]);
                UpdateAddressList();
                return;
            }
        }

    }

    public String translateAddress(String ip) {
        for (JButton tbutton : addressList.keySet()) {
            if (tbutton.getText().equals(ip)) {
                if (addressList.containsKey(tbutton)) {
                    return addressList.get(tbutton);
                }
            }
        }
        return ip;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (addressList.containsKey(e.getSource())) {
            messager.SetAddres(addressList.get(e.getSource()));
        } else if (e.getSource() == addNewIndex) {
            if (newAddrName.getText().length() > 0 && newAddrIP.getText().length() > 0) {
                AddAddress(newAddrName.getText(), newAddrIP.getText());
                newAddrName.setText("");
                newAddrIP.setText("");
            }
        } else if (e.getSource() == removeIndex) {
            RemoveAddress(newAddrName.getText());
            newAddrName.setText("");
            newAddrIP.setText("");
        }
    }
}