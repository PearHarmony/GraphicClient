package org.pearharmony.UI;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Panel;

public class GraphicWindow extends JFrame{

    Panel interaction = new Panel(new GridLayout(3,1));
    
    public GraphicWindow(){
        setTitle("PearHarmony");

        setSize(1000, 700);

        setLayout(new GridLayout(1,2));

        add(interaction);



        setVisible(true);
    }
}
