package org.pearharmony.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Messager extends JPanel implements ActionListener{
    JScrollPane pane;
    JPanel content = new JPanel();
    JTextField input = new JTextField(20);
    JButton send;

    GraphicWindow window;    

    public Messager(GraphicWindow window){
        this.window = window;

        setBackground(Color.CYAN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(300, 0, 500, 700);

        JPanel namePanel = new JPanel();
        JTextField name2 = new JTextField("Ich bin ajdiw");
        namePanel.add(name2);
        namePanel.setBackground(Color.DARK_GRAY);
        namePanel.setPreferredSize(new Dimension(490, 10));

        add(namePanel);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        pane = new JScrollPane(content);
        pane.setWheelScrollingEnabled(true);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pane.setPreferredSize(new Dimension(600,500));

        add(pane);
    
        JPanel inputPanel = new JPanel();

        
        inputPanel.setPreferredSize(new Dimension(300, 20));
        input.addActionListener(this);
        inputPanel.add(input);
        inputPanel.setBackground(Color.ORANGE);
        send = new JButton("Send");
        send.addActionListener(this);
        inputPanel.add(send);

        add(inputPanel);
    }

    public void AddMessage(String msg){
        JTextField newMsg = new JTextField(msg);
        newMsg.setPreferredSize(new Dimension(490, 25));
        content.add(newMsg);

        window.Update();
    }

    public void AddMessage(Image image){
        Icon icon = new ImageIcon(image);
        JLabel picture = new JLabel(icon);
        picture.setPreferredSize(new Dimension(490, 25));
        content.add(picture);

        window.Update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send || e.getSource() == input){
            AddMessage(input.getText());
            input.setText("");
        }
    }
}
