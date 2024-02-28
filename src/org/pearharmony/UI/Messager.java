package org.pearharmony.UI;

import java.awt.Color;
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

public class Messager extends JPanel implements ActionListener{
    JScrollPane pane;
    JPanel content = new JPanel();
    JTextField address = new JTextField(16);
    JTextField input = new JTextField(20);
    JButton send;
    JButton imgButton;

    GraphicWindow grapWindow;    

    public Messager(GraphicWindow window){
        this.grapWindow = window;

        setBackground(Color.CYAN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(300, 0, 500, 700);

        JPanel namePanel = new JPanel();
        JTextField name2 = new JTextField("Ich bin ajdiw");
        namePanel.add(name2);
        namePanel.setBackground(Color.DARK_GRAY);
        namePanel.setSize(200, 10);
        //namePanel.setPreferredSize(new Dimension(490, 10));
        //namePanel.setMaximumSize(new Dimension(999,100));
        //namePanel.setMinimumSize(new Dimension(400,100));

        //add(namePanel);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        pane = new JScrollPane(content);
        pane.setWheelScrollingEnabled(true);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pane.setPreferredSize(new Dimension(900,800));

        add(pane);
    
        JPanel inputPanel = new JPanel();

        
        inputPanel.setSize(new Dimension(300, 20));
        input.addActionListener(this);
        inputPanel.add(address);
        inputPanel.add(input);
        inputPanel.setBackground(Color.ORANGE);
        send = new JButton("Send");
        send.addActionListener(this);
        
        imgButton = new JButton("Img");
        imgButton.addActionListener(this);

        inputPanel.add(send);
        inputPanel.add(imgButton);

        add(inputPanel);
    }

    public void AddMessage(String sender, String msg){
        JTextField newMsg = new JTextField(sender + ": " + msg);
        //newMsg.setPreferredSize(new Dimension(999, 25));
        newMsg.setMaximumSize(new Dimension(990, 25));
        content.add(newMsg);

        if(grapWindow != null)
            grapWindow.Update();
    }

    public void AddMessage(String sender, Image image, Path path){
        pictureBox box =  new pictureBox(image,path);

        JButton picture = new JButton(sender + ": Bild");
        picture.addActionListener(box);
        picture.setPreferredSize(new Dimension(490, 25));
        content.add(picture);

        //pane.

        grapWindow.Update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send || e.getSource() == input){
            AddMessage("ich",input.getText());
            input.setText("");
        }
        else if(e.getSource() == imgButton)
        {
            try{
                File selectedFile = new File("");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    Image img = ImageIO.read(selectedFile);
                    AddMessage("ich", img, selectedFile.toPath());
                }
            }catch (Exception ex){

            }
        }
    }
}
