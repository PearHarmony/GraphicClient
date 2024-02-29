package org.pearharmony.UI;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.nio.file.Path;


public class GraphicWindow extends JFrame{

    Messager messager;
    AddressList addressList = new AddressList();

    public GraphicWindow(){
        setTitle("PearHarmony");

        setSize(810, 710);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messager = new Messager(this);

        add(messager);
        //add(addressList);
        //add(new JPanel());

        setVisible(true);
    }

    public void ReciveMSG(String sender,String msg){
        messager.AddMessage(sender,msg);
    }

    public void ReciveMSG(String sender,Image msg, Path path){
        messager.AddMessage(sender,msg, path);
    }
    public void ReciveMSG(String sender, Path path){
        try{
            Image msg = ImageIO.read(path.toFile());
            messager.AddMessage(sender,msg, path);
        }
        catch (Exception e){

        }
    }

    public void Update(){
        revalidate();
    }

}
