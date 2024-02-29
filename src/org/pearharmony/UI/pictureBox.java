package org.pearharmony.UI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class pictureBox implements ActionListener{

    Image img;
    Path path;

    public pictureBox(Image image, Path path){
        img = image;
        this.path = path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Icon icon = new ImageIcon(img);
        JLabel picture = new JLabel(icon);
        JOptionPane.showMessageDialog(null,picture,"Picture: " + path.toAbsolutePath().toString(),0);
    }
}