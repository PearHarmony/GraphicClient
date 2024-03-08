// @Daniel

package org.pearharmony.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.pearharmony.Control.Control;
import org.pearharmony.Data.Messages.ImageMessage;
import org.pearharmony.Data.Messages.Message;
import org.pearharmony.Data.Messages.TextMessage;
import org.pearharmony.UI.CustomBoxes.AudioBox;
import org.pearharmony.UI.CustomBoxes.PictureBox;
import org.pearharmony.Data.Messages.SoundMessage;

public class Messager extends JPanel implements ActionListener {
    private AddressList addressList;

    private JScrollPane messagePane;
    private JPanel content = new JPanel();
    private JTextField addressInput = new JTextField(20);
    private JTextField textInput = new JTextField(20);
    private JButton sendText;
    private JButton sendFile;

    private GraphicWindow grapWindow;
    private Control control;

    public Messager(GraphicWindow window, Control controll) {
        // init
        this.grapWindow = window;
        this.control = controll;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(300, 0, 550, 700);

        // Headline
        JPanel namePanel = new JPanel();
        JTextField headline = new JTextField("Nachrichtenfeld");
        headline.setEditable(false);
        namePanel.add(headline);
        namePanel.setSize(200, 10);

        add(namePanel);

        // address list space
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        messagePane = new JScrollPane(content);
        messagePane.setWheelScrollingEnabled(true);
        messagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        messagePane.setPreferredSize(new Dimension(900, 800));

        add(messagePane);

        // input 
        JPanel inputPanel = new JPanel();
        inputPanel.setMaximumSize(new Dimension(450, 70));
        inputPanel.setPreferredSize(new Dimension(450, 70));

        JTextField addressInfo = new JTextField("Addresse:", 8);
        addressInput.setToolTipText("Addresse");
        addressInfo.setEditable(false);
        inputPanel.add(addressInfo);

        inputPanel.add(addressInput);

        sendFile = new JButton("Img/Wav");
        sendFile.addActionListener(this);
        inputPanel.add(sendFile);

        JTextField nameInfo = new JTextField("Nachricht:", 8);
        textInput.setToolTipText("Message");
        nameInfo.setEditable(false);
        inputPanel.add(nameInfo);

        textInput.addActionListener(this);
        inputPanel.add(textInput);

        sendText = new JButton("-Send-");
        sendText.addActionListener(this);
        inputPanel.add(sendText);

        add(inputPanel);
    }

    public void addMessage(String sender, String msg) {
        JTextField newMsg = new JTextField(changeAddress(sender) + ": " + msg);
        newMsg.setEditable(false);
        newMsg.setMaximumSize(new Dimension(990, 25));
        content.add(newMsg);

        if (grapWindow != null)
            grapWindow.revalidate();
    }

    public void addMessage(String sender, Path path) {
        content.add(new PictureBox(changeAddress(sender), path));

        grapWindow.revalidate();
    }

    public void addSound(String sender, Path path, boolean autoStart) {
        content.add(new AudioBox(changeAddress(sender), path, autoStart));

        grapWindow.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendText || e.getSource() == textInput) { 
            // send message
            if (textInput.getText() == "")
                return;
            String msg = textInput.getText();
            Message message = new TextMessage(addressList.translateAddress(addressInput.getText()), msg);
            addMessage("ich -> " + addressList.translateAddress(addressInput.getText()), msg);
            control.send(message);

            textInput.setText("");
        } else if (e.getSource() == sendFile) { 
            // send file (png / wav)
            try {
                // select file
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    String addresse = addressList.translateAddress(addressInput.getText());

                    // send File
                    String extention = getExtention(selectedFile);

                    if (extention.equals("png")) {
                        // pictures
                        Message message = new ImageMessage(addresse, selectedFile.toPath());

                        addMessage("ich -> " + addresse, selectedFile.toPath());
                        control.send(message);
                    } else if (extention.equals("wav")) {
                        // audio (.mp3 not suported by Libary) 
                        Message message = new SoundMessage(addresse, selectedFile.toPath());

                        addSound("ich -> " + addresse, selectedFile.toPath(), false);
                        control.send(message);
                    } else { 
                        // File not supportet
                        addMessage("ERROR", "File not Suportet");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setAddres(String address) {
        this.addressInput.setText(address);
    }

    public void setAddressList(AddressList addressList) {
        this.addressList = addressList;
    }

    private String getExtention(File file) {
        String name = file.getName();
        String[] split = name.split("[.]");

        if (split.length > 0)
            return split[split.length - 1];
        return name;
    }
    
    private String changeAddress(String address){
        String[] split = address.split(" ");

        address = "";
        for (String string : split) {
            address += addressList.translateAddressName(string) + " ";
        }
        return address.trim();
    }
}
