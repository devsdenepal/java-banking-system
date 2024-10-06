package oopp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerMenu extends JFrame{
	
    
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    
    JButton transfer=new JButton("Transfer Money");
    JButton balance=new JButton("View/Deposit/Withdraw Money");
    JButton back=new JButton("Back");
    
    
    public CustomerMenu(){
 
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(1,2));
        p2.setLayout(new GridLayout(1,1));
        setLayout(new GridLayout(5,5));
        transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
	         	MoneyTransferMenu f = new MoneyTransferMenu();
	         	MainGUI.goToFrame(f);
            }

        });
        
        balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
	         	BalanceMenu f = new BalanceMenu("customer");
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
        
        p1.add(transfer);
        p1.add(balance);
        
        p2.add(back);
        
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
          
}
}
