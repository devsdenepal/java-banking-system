package oopp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EmployeeMenu extends JFrame{
	
    
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    
    JLabel lmessage = new JLabel("Action");
    
    JTextField message = new JTextField(30);
    
    JButton account=new JButton("Add/Remove/Search Account");
    JButton balance=new JButton("View/Deposit/Withdraw Money");
    JButton back=new JButton("Back");
    
    
    public EmployeeMenu(){
 
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(2,1));
        p2.setLayout(new GridLayout(1,2));
        p3.setLayout(new GridLayout(1,1));
        setLayout(new GridLayout(5,5));
        
        message.setText("Welcome");
        
        account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
	         	AccountMenu f = new AccountMenu();
	         	MainGUI.goToFrame(f);
            }

        });
        
        balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
	         	BalanceMenu f = new BalanceMenu("employee");
	         	MainGUI.goToFrame(f);
            
            }
        });
        
        
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		dispose();
            	MainGUI f = new MainGUI();
            	MainGUI.goToFrame(f);
            }
        });
        
        
        p1.add(lmessage);
        p1.add(message);
        
        p2.add(account);
        p2.add(balance);
        
        p3.add(back);
        
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
          
}
}