package org.pearharmony.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

public class AudioBox extends JButton implements ActionListener {
    private Clip clip;

    public AudioBox(String address, Path audio) {
        setText(address + ": Sound");
        addActionListener(this);
        try {
            AudioInputStream asj = AudioSystem.getAudioInputStream(audio.toFile());
            clip = AudioSystem.getClip();
            clip.open(asj);
        } catch (Exception e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clip.start();
    }
}
