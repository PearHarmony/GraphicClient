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

    public AudioBox(String address, Path audio, boolean autoStart) {
        setText(address + ": Sound (" + audio.toString() + ")");
        addActionListener(this);
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(audio.toFile());
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            inputStream.close();
            if(autoStart){
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }                
        } catch (Exception e) {
            System.out.println(audio.toFile().getName());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(clip.isRunning()){
            clip.stop();
        }else{
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }
}
