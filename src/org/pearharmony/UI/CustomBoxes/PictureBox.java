// @Daniel

package org.pearharmony.UI.CustomBoxes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PictureBox extends JButton implements ActionListener {

    private Image img;
    private Path path;

    public PictureBox(String sender, Path path) {
        try {
            img = ImageIO.read(path.toFile());
            this.path = path;

            setText(sender + ": Bild (" + path.toString() + ")");
            addActionListener(this);
        } catch (IOException e) {
            setText(sender + ": Bild (Faild To Load)");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Icon icon = new ImageIcon(img);
        JLabel picture = new JLabel(icon);
        JOptionPane.showMessageDialog(null, picture, "Picture: "
                + path.toAbsolutePath().toString(), JOptionPane.INFORMATION_MESSAGE);
    }
}
