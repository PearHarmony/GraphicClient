package org.pearharmony;

import java.awt.Image;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import org.pearharmony.UI.*;

public class App {
    public static void main(String[] args) {
        GraphicWindow window1 = new GraphicWindow();
        try {
            File file = new File("a.png");
            Image img = ImageIO.read(file);
            window1.ReciveMSG("main", img, file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
