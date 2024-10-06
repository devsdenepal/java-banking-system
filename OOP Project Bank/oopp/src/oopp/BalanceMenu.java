package oopp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BalanceMenu extends JFrame {
	
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    

    JLabel lIBAN=new JLabel("IBAN");
    JLabel lAmmount=new JLabel("Amount");
    
    JTextField tfIBAN=new JTextField(8);
    JTextField tfBal=new JTextField(8);
    
    JButton deposit =new JButton("Deposit Amount");
    JButton withdraw =new JButton("Withdraw Amount");
    JButton view =new JButton("View Amount");
    JButton back =new JButton("Back");
    
     

    public BalanceMenu(String backMenu){
    	
    	File f = new File("acc.dat");
        Filing afile = new Filing(f);
        
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(2,2));
        p2.setLayout(new GridLayout(1,3));
        p3.setLayout(new GridLayout(1,4));
        setLayout(new GridLayout(5,5));
        
        
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		dispose();
        		if(backMenu.equals("employee")) {
                	EmployeeMenu f = new EmployeeMenu();
                	MainGUI.goToFrame(f);
        		}
        		else {
        			CustomerMenu f = new CustomerMenu();
                	MainGUI.goToFrame(f);
        		}
            	
            }
        });
        
        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	Customer c;
				try {
					c = afile.searchAccount(tfIBAN.getText());
					if(c==null) {
	            		tfIBAN.setText("No such account Exists");
	            	}
	            	else {
	            		if(MainGUI.alphanumericCheck(tfIBAN, 11) && MainGUI.balanceCheck(tfBal, 500, c.getAccount().transactionLimit)) {
	                    	afile.deposit(tfIBAN.getText(), Double.parseDouble(tfBal.getText()));
	            	}
	            	
	            	}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
            	
            }

        });
        p3.add(deposit);
        
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	Customer c;
				try {
					c = afile.searchAccount(tfIBAN.getText());
					if(c==null) {
	            		tfIBAN.setText("No such account Exists");
	            	}
	            	else {
	            		if(MainGUI.alphanumericCheck(tfIBAN, 11) && MainGUI.balanceCheck(tfBal, 500, c.getAccount().transactionLimit)) {
	                    	afile.withdraw(tfIBAN.getText(), Double.parseDouble(tfBal.getText()));
	            	}
	            	
	            	}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
            }
        });
        p3.add(withdraw);
        
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	Customer c;
				try {
					c = afile.searchAccount(tfIBAN.getText());
					if(c==null) {
	            		tfIBAN.setText("No such account Exists");
	            	}
	            	else {
	            		if(MainGUI.alphanumericCheck(tfIBAN, 11)) {
	            			tfBal.setText(String.valueOf(c.getAccount().getBalance()));
	            	}
	            	}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
            }
        });
        p3.add(view);
        

        
        p1.add(lIBAN);
        p1.add(tfIBAN);
        p1.add(lAmmount);
        p1.add(tfBal);
        
        p2.add(deposit);
        p2.add(withdraw);
        p2.add(view);
        
        p3.add(back);

        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
          
}
}
