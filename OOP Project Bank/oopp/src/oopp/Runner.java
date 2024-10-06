package oopp;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class Runner {

    public static void main(String[] args) throws ClassNotFoundException, NullPointerException, IOException {
        
    	MainGUI frame=new MainGUI();
        frame.setSize(500,500);
        frame.setTitle("Bank");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

}
