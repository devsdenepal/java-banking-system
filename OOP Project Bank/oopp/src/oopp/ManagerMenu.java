package oopp;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerMenu extends JFrame{
	 
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    
    JLabel welcome = new JLabel("                   Welcome ");
    JLabel action = new JLabel("Action taken");
    
    JTextField list = new JTextField(300);
    
    JButton add=new JButton("Add/Remove/Search");
    JButton view=new JButton("View Employee list");
    JButton back=new JButton("Back");
    
    
    public ManagerMenu(){
    	 
    	File f = new File("Employees.dat");
	    Filing files = new Filing(f);
	    
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(2,2));
        p2.setLayout(new GridLayout(2,1));
        p3.setLayout(new GridLayout(3,1));
        setLayout(new GridLayout(5,5));
        welcome.setFont(new Font("Arial", Font.PLAIN, 24));
        
        

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
            	LoginMenu f = new LoginMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
            	EmployeeForm f = new EmployeeForm();
            	MainGUI.goToFrame(f);
            }
        });
        
        view.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent evt) {
            	try {
            		list.setText(files.displayEmployee());
                } catch (Exception e) {
                        e.printStackTrace();
                } 
            }
        });
        
	    p1.add(welcome);
        p2.add(add);
        p2.add(view);
        
        
        p3.add(action);
        p3.add(list);
        p3.add(back);
	   
	    add(p1,BorderLayout.NORTH);
	    add(p2,BorderLayout.CENTER);
	    add(p3, BorderLayout.SOUTH);
	      
		}
}
