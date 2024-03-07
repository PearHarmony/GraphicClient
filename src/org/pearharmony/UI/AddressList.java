// @Daniel

package org.pearharmony.UI;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

public class AddressList extends JPanel implements ActionListener {
    private Messager messager;
    private static final String addFileName = "addresses";

    private List<Entry<JButton, String>> addressList = new ArrayList<>();
    

    private JPanel AddrSelList = new JPanel();
    private JTextField newAddrName = new JTextField(15);
    private JTextField newAddrIP = new JTextField(15);
    private JButton addNewIndex = new JButton("Add");
    private JButton removeIndex = new JButton("Remove");

    public AddressList(Messager messager) {
        this.messager = messager;
        setBounds(0, 0, 300, 700);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        JTextField name = new JTextField("Addressen");
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
            File text = Paths.get(System.getProperty("user.dir"), addFileName).toFile();
            Scanner reader = new Scanner(text);
            String adrData[];
            while (reader.hasNextLine()) {
                adrData = reader.nextLine().split(" ");
                JButton button = new JButton(adrData[0]);
                button.addActionListener(this);
                addressList.add(new SimpleEntry<JButton,String>(button, adrData[1]));
            }           
            reader.close();
            UpdateAddressList();
        } catch (Exception e){

        }
    }

    public void UpdateAddressList() {
        AddrSelList.removeAll();

        addressList.forEach(a -> AddrSelList.add(a.getKey()));
        
        AddrSelList.repaint();
        revalidate();

        try{            
            File text = Paths.get(System.getProperty("user.dir"), addFileName).toFile();
            FileWriter writer = new FileWriter(text);

            addressList.forEach(a -> {
                try {
                    writer.write(a.getKey().getText() + " " + a.getValue() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
         
            writer.close();
        } catch (Exception e){

        }
    }

    public void AddAddress(String name, String address) {
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.add(new SimpleEntry<JButton,String>(button, address));

        UpdateAddressList();
    }

    public void RemoveAddress(String name) {
        // get Entry of the Butten with the name
        Optional<Entry<JButton, String>> entry = addressList.stream()
                .filter(obj -> obj.getKey().getText().equals(name)).findFirst();

        if(entry != null && entry.isPresent())
        addressList.remove(entry.get());
        UpdateAddressList();
    }

    public String translateAddress(String ip) {
        // get Entry of the Butten with the ip
        Optional<Entry<JButton, String>> entry = addressList.stream()
                .filter(obj -> obj.getKey().getText().equals(ip)).findFirst();

        if(entry != null && entry.isPresent())
            return entry.get().getValue();
        return ip;
    }

    public String translateAddressName(String ip) {
        // get Entry of the Butten with the ip
        Optional<Entry<JButton, String>> entry = addressList.stream()
                .filter(obj -> obj.getValue().equals(ip)).findFirst();

        if(entry != null && entry.isPresent())
            return entry.get().getKey().getText();
        return ip;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addressList.forEach(a -> { 
            if (a.getKey().equals(e.getSource()))
                messager.SetAddres(a.getValue());
        });
        if (e.getSource() == addNewIndex) {
            if (newAddrName.getText().length() > 0 && newAddrIP.getText().length() > 0 &&           // ip or name cant be empty
                !newAddrName.getText().contains(" ") && !newAddrIP.getText().contains(" "))     // ip and name cannot contain spaces
            {
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