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
    AddressList addressList;

    JScrollPane messagePane;
    JPanel content = new JPanel();
    JTextField addressInput = new JTextField(20);
    JTextField textInput = new JTextField(20);
    JButton sendText;
    JButton sendFile;

    GraphicWindow grapWindow;
    Control control;

    public Messager(GraphicWindow window, Control controll) {
        this.grapWindow = window;
        this.control = controll;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(300, 0, 550, 700);

        JPanel namePanel = new JPanel();
        JTextField headline = new JTextField("Nachrichtenfeld");
        headline.setEditable(false);
        namePanel.add(headline);
        namePanel.setSize(200, 10);

        add(namePanel);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        messagePane = new JScrollPane(content);
        messagePane.setWheelScrollingEnabled(true);
        messagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        messagePane.setPreferredSize(new Dimension(900, 800));

        add(messagePane);

        JPanel inputPanel = new JPanel();
        inputPanel.setMaximumSize(new Dimension(450, 70));
        inputPanel.setPreferredSize(new Dimension(450, 70));

        JTextField addressInfo = new JTextField("Addresse:", 8);
        JTextField nameInfo = new JTextField("Nachricht:", 8);

        addressInfo.setEditable(false);
        nameInfo.setEditable(false);

        addressInput.setToolTipText("Addresse");

        textInput.setToolTipText("Message");
        textInput.addActionListener(this);

        sendText = new JButton("-Send-");
        sendText.addActionListener(this);

        sendFile = new JButton("Img/Wav");
        sendFile.addActionListener(this);

        inputPanel.add(addressInfo);
        inputPanel.add(addressInput);
        inputPanel.add(sendFile);
        inputPanel.add(nameInfo);
        inputPanel.add(textInput);
        inputPanel.add(sendText);

        add(inputPanel);
    }

    public void AddMessage(String sender, String msg) {
        JTextField newMsg = new JTextField(sender + ": " + msg);
        newMsg.setEditable(false);
        newMsg.setMaximumSize(new Dimension(990, 25));
        content.add(newMsg);

        if (grapWindow != null)
            grapWindow.revalidate();
    }

    public void AddMessage(String sender, Path path) {
        content.add(new PictureBox(sender, path));

        grapWindow.revalidate();
    }

    public void AddSound(String sender, Path path, boolean autoStart) {
        content.add(new AudioBox(sender, path, autoStart));

        grapWindow.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendText || e.getSource() == textInput) {
            if (textInput.getText() == "")
                return;
            String msg = textInput.getText();
            Message message = new TextMessage(addressList.translateAddress(addressInput.getText()), msg);
            AddMessage(addressList.translateAddress(addressInput.getText()), msg);
            control.Send(message);

            textInput.setText("");
        } else if (e.getSource() == sendFile) {
            try {
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(GetExtention(selectedFile));
                    String addresse = addressList.translateAddress(addressInput.getText());

                    String extention = GetExtention(selectedFile);

                    if (extention.equals("png")) {
                        Message message = new ImageMessage(addresse, selectedFile.toPath());

                        
                        AddMessage("ich -> " + addresse, selectedFile.toPath());
                        control.Send(message);
                    } else if (extention.equals("wav")) {
                        Message message = new SoundMessage(addresse, selectedFile.toPath());

                        AddSound("ich -> " + addresse, selectedFile.toPath(), false);
                        control.Send(message);
                    } else {
                        AddMessage("ERROR", "File not Suportet");
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void SetAddres(String address) {
        this.addressInput.setText(address);
    }

    public void SetAddressList(AddressList addressList) {
        this.addressList = addressList;
    }

    private String GetExtention(File file) {
        String name = file.getName();
        String[] split = name.split("[.]");

        if (split.length > 0)
            return split[split.length - 1];
        return name;
    }
}
