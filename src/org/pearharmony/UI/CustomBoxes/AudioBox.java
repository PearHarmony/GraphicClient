// @Daniel

package org.pearharmony.UI.CustomBoxes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

public class AudioBox extends JButton implements ActionListener {
    private Clip clip;

    public AudioBox(String address, Path path, boolean autoStart) {
        
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path.toFile());
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            inputStream.close();
            if(autoStart){
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }     
            setText(address + ": Sound (" + path.toString() + ")");
            addActionListener(this);           
        } catch (Exception e) {
            setText(address + ": Sound (Faild to Load)");
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
