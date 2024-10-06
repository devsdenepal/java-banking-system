package oopp;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class MoneyTransferMenu extends JFrame{
	
	 	JPanel p1=new JPanel();
	    JPanel p2=new JPanel();
	    

	    JLabel lSenderIBAN=new JLabel("Sender IBAN");
	    JLabel lBalance=new JLabel("Balance");
	    JLabel lRecieverIBAN=new JLabel("Reciever IBAN");
	    
	    JTextField tfSenderIBAN=new JTextField(8);
	    JTextField tfReciverIBAN=new JTextField(8);
	    JTextField tfBalance=new JTextField(8);
	    
	    
	    JButton send =new JButton("Send");
	    JButton back =new JButton("Back");
	    
	    

	    public MoneyTransferMenu(){
	    	
	    	File f = new File("acc.dat");
	    	Filing afile = new Filing(f);
	    	
	        setLayout(new BorderLayout(3,5));
	        p1.setLayout(new GridLayout(3,2));
	        p2.setLayout(new GridLayout(1,2));
	        setLayout(new GridLayout(5,5));
	        
	        back.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent evt) {
	        		dispose();
	            	CustomerMenu f = new CustomerMenu();
	            	MainGUI.goToFrame(f);
	            }
	        });
	        
	        send.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	
	            	try {
	            		Customer c1 = afile.searchAccount(tfSenderIBAN.getText());
	            		Customer c2 = afile.searchAccount(tfReciverIBAN.getText());
	            		if(c1==null || c2==null) {
	            			tfReciverIBAN.setText("No such Account");
	            			tfSenderIBAN.setText("No such Account");
	            		}
	            		else {
	            			if(MainGUI.balanceCheck(tfBalance, 500, c1.getAccount().transactionLimit)) {
	            				afile.transfer(tfSenderIBAN.getText(), tfReciverIBAN.getText(), Double.parseDouble(tfBalance.getText()));
	            			}
	            			
	            		}
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
	            	}
	        });
	        
	        
	        p1.add(lSenderIBAN);
	        p1.add(tfSenderIBAN);
	        p1.add(lRecieverIBAN);
	        p1.add(tfReciverIBAN);
	        p1.add(lBalance);
	        p1.add(tfBalance);

 
	        p2.add(send);
	        p2.add(back);
	        
	        add(p1,BorderLayout.NORTH);
	        add(p2,BorderLayout.CENTER);
	          
	}
}
