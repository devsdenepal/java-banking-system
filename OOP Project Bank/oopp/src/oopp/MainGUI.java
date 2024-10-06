package oopp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainGUI extends JFrame{

    
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    
    
    JLabel welcome=new JLabel("                 Welcome to Sasta Bank");
    
    JButton employee=new JButton("Employee");
    JButton manager = new JButton("Manager");
    JButton customer=new JButton("Customer");
    
    static Pattern letter = Pattern.compile("[a-zA-z]");
    static Pattern digit = Pattern.compile("[0-9]");
    static Pattern special = Pattern.compile ("[!@#$%&*()_+.=|<>?{}\\[\\]~-]");
    
    public MainGUI(){
    	
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(2,2));
        p2.setLayout(new GridLayout(2,1));
        setLayout(new GridLayout(5,5));
        welcome.setFont(new Font("Arial", Font.PLAIN, 24));
        
        
        
        employee.setBackground(Color.orange);
        employee.setForeground(Color.GRAY);
        customer.setBackground(Color.orange);
        customer.setForeground(Color.GRAY);
        manager.setBackground(Color.orange);
        manager.setForeground(Color.GRAY);
        
        employee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
            	LoginMenu f = new LoginMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
        customer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
            	dispose();
            	LoginMenu f = new LoginMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
        manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
            	dispose();
            	LoginMenu f = new LoginMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
        manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { 
            	dispose();
            	ManagerMenu f = new ManagerMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
	    p1.add(welcome);
        p2.add(employee);
        p2.add(customer);
        p2.add(manager);
        
	   
	    add(p1,BorderLayout.NORTH);
	    add(p2,BorderLayout.CENTER);
	      
		}
    
    
    public static void goToFrame(JFrame f) {
    	f.setVisible(true);
    	f.setSize(500,500);
        f.setTitle("Login");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    
    public static boolean nameCheck(JTextField tf) {
    	
    	String a = tf.getText();
        Matcher hasLetter = letter.matcher(a);
        Matcher hasDigit = digit.matcher(a);
        Matcher hasSpecial = special.matcher(a);
        
        if(hasLetter.find() && !hasDigit.find() && !hasSpecial.find()) {
        	return true;
        }
        else {
        	tf.setText("Invalid Input.");
        	return false;
        }

        
    	
    }
    
    public static boolean digitCheck(JTextField tf, int lenmin, int lenmax) {
    	
    	String a = tf.getText();
        Matcher hasLetter = letter.matcher(a);
        Matcher hasDigit = digit.matcher(a);
        Matcher hasSpecial = special.matcher(a);
        
        if(!hasLetter.find() && hasDigit.find() && !hasSpecial.find() && a.length()>=lenmin && a.length()<=lenmax) {
        	return true;
        }
        else {
        	tf.setText("Invalid Input.");
        	return false;
        }
    }
    
    public static boolean alphanumericCheck(JTextField tf, int len) {
    	
    	String a = tf.getText();
        Matcher hasLetter = letter.matcher(a);
        Matcher hasDigit = digit.matcher(a);
        Matcher hasSpecial = special.matcher(a);
        
        if(hasLetter.find() && hasDigit.find() && !hasSpecial.find() && a.length()==len) {
        	return true;
        }
        else {
        	tf.setText("Invalid Input.");
        	return false;
        }
    }
    
    public static boolean balanceCheck(JTextField tf, double min, double max) {
    	
    	String a = tf.getText();
        Matcher hasLetter = letter.matcher(a);
        Matcher hasDigit = digit.matcher(a);
        Matcher hasSpecial = special.matcher(a);
        
        if(!hasLetter.find() && hasDigit.find() && !hasSpecial.find()) {
        	if(Double.parseDouble(tf.getText())>=min && Double.parseDouble(tf.getText())<=max) {
        		return true;
        	}
        	else {
        		tf.setText("Balnce value not correct");
        		return false;
        	}
        }
        else {
        	tf.setText("Invalid Input.");
        	return false;
        }
    }
    
    public static boolean radioCheck(JLabel l , JRadioButton rbtn1, JRadioButton rbtn2)  {
    	if(rbtn1.isSelected() || rbtn2.isSelected()) {
    		return true;
    	}
    	l.setText("Please select " + l.getText());
    	return false;
    }
    
}


