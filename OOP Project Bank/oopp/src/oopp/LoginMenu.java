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

public class LoginMenu extends JFrame{
	
	JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    
    JLabel lMessage = new JLabel("Message");
    JLabel lPass=new JLabel("Password");
    JLabel lCnic=new JLabel("CNIC");
    JLabel lCustomer = new JLabel("Customer");
    JLabel lEmployee = new JLabel("Employee");
    JLabel lManager = new JLabel("Manager");
    JLabel lStakeholder = new JLabel("You are ");
    
    JTextField tfCnic=new JTextField(8);
    JTextField tfPass=new JTextField(8);
    JTextField tfMessage = new JTextField(8);
    
    JRadioButton customer = new JRadioButton();
    JRadioButton employee = new JRadioButton();
    JRadioButton manager = new JRadioButton();
    ButtonGroup stakeholder = new ButtonGroup();
    
    JButton next=new JButton("Next");
    JButton back=new JButton("Back");
	
	public LoginMenu() {
		
		File f1 = new File("acc.dat");
    	Filing afile = new Filing(f1);
    	
    	File f2 = new File("Employees.dat");
        Filing efile = new Filing(f2);
        
        String managerpass = "manager";
        String managerCNIC = "34402";
    	
		setLayout(new BorderLayout(3,5));
	    p1.setLayout(new GridLayout(3,2));
	    p2.setLayout(new GridLayout(1,6));
	    p3.setLayout(new GridLayout(1,2));
	    setLayout(new GridLayout(5,5));
	    tfMessage.setText("Welcome to UMU Bank");
	    
	    back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		
	    		dispose();
				MainGUI f = new MainGUI();
			 	MainGUI.goToFrame(f);

	    	}

        });
	    
	    
	    next.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		try {
					if(customer.isSelected()) {
						if(afile.searchAccount(tfCnic.getText())!= null) {
							if(afile.searchAccount(tfCnic.getText()).getPassword().equals(tfPass.getText())){
								setVisible(false);
								CustomerMenu f = new CustomerMenu();
								f.setVisible(true);
								f.setSize(500,500);
								f.setTitle("Login");
								f.setVisible(true);
								f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							}
							else {
							tfMessage.setText("Password or CNIC is incorrect");
							}
						}
						else {
							tfMessage.setText("No person with such CNIC exists");
						}
					}
					else if (employee.isSelected()) {

						if(efile.searchEmployee(tfCnic.getText())!=null) {
							if(efile.searchEmployee(tfCnic.getText()).getPassword().equals(tfPass.getText())){
							
								setVisible(false);
								EmployeeMenu f = new EmployeeMenu();
							 	f.setVisible(true);
							 	f.setSize(500,500);
							    f.setTitle("Login");
							    f.setVisible(true);
							    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							}
							else {
							tfMessage.setText("Password or CNIC is incorrect");
							}
						}
						else {
							tfMessage.setText("No person with such CNIC exists");
						}
					}
					
					else if (manager.isSelected()) {
					
						if(managerCNIC.equals(tfCnic.getText()) && managerpass.equals(tfPass.getText())){
							
							setVisible(false);
							ManagerMenu f = new ManagerMenu();
						 	MainGUI.goToFrame(f);
						    
						}
						else {
							tfMessage.setText("Password or CNIC is incorrect");
						}
					
					}
					else {
					tfMessage.setText("No person type selected");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
	    		}
		    	

        });
        
	    
	    stakeholder.add(customer);
	    stakeholder.add(employee);
	    stakeholder.add(manager);
	    
	    p1.add(lMessage);
        p1.add(tfMessage);
        p1.add(lCnic);
        p1.add(tfCnic);
        p1.add(lPass);
        p1.add(tfPass);
        
        p2.add(lStakeholder);
        p2.add(lCustomer);
        p2.add(customer);
        p2.add(lEmployee);
        p2.add(employee);
        p2.add(lManager);
        p2.add(manager);
        
        p3.add(next);
        p3.add(back);
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
	}
}
