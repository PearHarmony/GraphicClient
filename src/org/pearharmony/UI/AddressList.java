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
        // init basic
        this.messager = messager;
        setBounds(0, 0, 300, 700);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        AddrSelList.setLayout(new BoxLayout(AddrSelList, BoxLayout.Y_AXIS));

        // Headline
        JTextField name = new JTextField("Addressen");
        name.setMaximumSize(new Dimension(400, 40));
        name.setEditable(false);
        add(name);

        // Text Panel
        JScrollPane list = new JScrollPane(AddrSelList);
        list.setWheelScrollingEnabled(true);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(list);

        // Input
        JPanel addPanel = new JPanel();
        addPanel.setMaximumSize(new Dimension(400, 90));
        addPanel.setPreferredSize(new Dimension(400, 90));

        addNewIndex.addActionListener(this);
        removeIndex.addActionListener(this);

        JTextField nam = new JTextField("Name:", 8);
        nam.setEditable(false);
        addPanel.add(nam);
        
        newAddrName.setToolTipText("Name");
        addPanel.add(newAddrName);

        JTextField adr = new JTextField("Addresse:", 8);
        adr.setEditable(false);
        addPanel.add(adr);

        newAddrIP.setToolTipText("IP");
        addPanel.add(newAddrIP);

        addPanel.add(addNewIndex);
        addPanel.add(removeIndex);

        add(addPanel);

        readAddresses();
    }

    public void readAddresses(){
        try{            
            File text = Paths.get(System.getProperty("user.dir"), addFileName).toFile();
            if(!text.exists()){
                text.createNewFile();
                addAddress("localhost", "127.0.0.1");
            } else {
                Scanner reader = new Scanner(text);
                String adrData[];

                while (reader.hasNextLine()) {
                    adrData = reader.nextLine().split(" ");
                    JButton button = new JButton(adrData[0]);
                    button.addActionListener(this);
                    addressList.add(new SimpleEntry<JButton,String>(button, adrData[1]));
                }           
                reader.close();
            }

            updateAddressList();
        } catch (Exception e){
            
            messager.addMessage("ERROR", e.toString());
        }
    }

    public void updateAddressList() {
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
                    messager.addMessage("ERROR", e.toString());
                }
            });
         
            writer.close();
        } catch (Exception e){
            messager.addMessage("ERROR", e.toString());
        }
    }

    public void addAddress(String name, String address) {
        JButton button = new JButton(name);
        button.addActionListener(this);
        addressList.add(new SimpleEntry<JButton,String>(button, address));

        updateAddressList();
    }

    public void removeAddress(String name) {
        // get the first Entry of the Butten with the name
        Optional<Entry<JButton, String>> entry = addressList.stream()
                .filter(obj -> obj.getKey().getText().equals(name)).findFirst();

        if(entry != null && entry.isPresent()){
            addressList.remove(entry.get());
            updateAddressList();
        } else {
            messager.addMessage("Addresslist", "");
        }
    }

    public String translateAddress(String ip) {
        // get the first Entry of the Butten with the ip
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
        // address button
        addressList.forEach(a -> { 
            if (a.getKey().equals(e.getSource()))
                messager.setAddres(a.getValue());
        });

        if (e.getSource() == addNewIndex) {
            // Add new address
            if (newAddrName.getText().length() > 0 && newAddrIP.getText().length() > 0 &&           // ip or name cant be empty
                !newAddrName.getText().contains(" ") && !newAddrIP.getText().contains(" "))     // ip and name cannot contain spaces
            {
                addAddress(newAddrName.getText(), newAddrIP.getText());
                newAddrName.setText("");
                newAddrIP.setText("");
            }
        } else if (e.getSource() == removeIndex) {
             // remove address
            removeAddress(newAddrName.getText());
            newAddrName.setText("");
            newAddrIP.setText("");
        }
    }
}