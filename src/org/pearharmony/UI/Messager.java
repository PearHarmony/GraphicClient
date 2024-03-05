package org.pearharmony.UI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;
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
import org.pearharmony.Data.Messages.SoundMessage;

public class Messager extends JPanel implements ActionListener {
    AddressList addressList;

    JScrollPane pane;
    JPanel content = new JPanel();
    JTextField address = new JTextField(20);
    JTextField input = new JTextField(20);
    JButton send;
    JButton imgButton;

    GraphicWindow grapWindow;
    Control controll;

    public Messager(GraphicWindow window, Control controll) {
        this.grapWindow = window;
        this.controll = controll;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(300, 0, 550, 700);

        JPanel namePanel = new JPanel();
        JTextField name2 = new JTextField("Nachrichtenfeld");
        namePanel.add(name2);
        namePanel.setSize(200, 10);

        add(namePanel);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        pane = new JScrollPane(content);
        pane.setWheelScrollingEnabled(true);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pane.setPreferredSize(new Dimension(900, 800));

        add(pane);

        JPanel inputPanel = new JPanel();
        inputPanel.setMaximumSize(new Dimension(450, 70));
        inputPanel.setPreferredSize(new Dimension(450, 70));

        JTextField addressInfo = new JTextField("Addresse:", 8);
        JTextField nameInfo = new JTextField("Nachricht:", 8);

        addressInfo.setEditable(false);
        nameInfo.setEditable(false);

        address.setToolTipText("Addresse");

        input.setToolTipText("Message");
        input.addActionListener(this);

        send = new JButton("-Send-");
        send.addActionListener(this);

        imgButton = new JButton("Img/Wav");
        imgButton.addActionListener(this);

        inputPanel.add(addressInfo);
        inputPanel.add(address);
        inputPanel.add(imgButton);
        inputPanel.add(nameInfo);
        inputPanel.add(input);
        inputPanel.add(send);

        add(inputPanel);
    }

    public void AddMessage(String sender, String msg) {
        JTextField newMsg = new JTextField(sender + ": " + msg);
        newMsg.setEditable(false);
        newMsg.setMaximumSize(new Dimension(990, 25));
        content.add(newMsg);

        if (grapWindow != null)
            grapWindow.Update();
    }

    public void AddMessage(String sender, Image image, Path path) {
        pictureBox box = new pictureBox(image, path);

        JButton picture = new JButton(sender + ": Bild (" + path.toString() + ")");
        picture.addActionListener(box);
        picture.setPreferredSize(new Dimension(490, 25));
        content.add(picture);

        grapWindow.Update();

    }

    public void AddSound(String sender, Path path, boolean autoStart) {
        content.add(new AudioBox(sender, path, autoStart));

        grapWindow.Update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send || e.getSource() == input) {
            if (input.getText() == "")
                return;
            String msg = input.getText();
            Message message = new TextMessage(addressList.translateAddress(address.getText()), msg);
            AddMessage(addressList.translateAddress(address.getText()), msg);
            controll.Send(message);

            input.setText("");
        } else if (e.getSource() == imgButton) {
            try {
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(GetExtention(selectedFile));
                    String addresse = addressList.translateAddress(address.getText());

                    if (GetExtention(selectedFile).equals("png")) {
                        Message message = new ImageMessage(addresse, selectedFile.toPath());

                        Image img = ImageIO.read(selectedFile.toPath().toFile());
                        AddMessage("ich -> " + addresse, img, selectedFile.toPath());
                        controll.Send(message);
                    } else if (GetExtention(selectedFile).equals("wav")) {
                        Message message = new SoundMessage(addresse, selectedFile.toPath());

                        AddSound("ich -> " + addresse, selectedFile.toPath(), false);
                        controll.Send(message);
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
        this.address.setText(address);
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
