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

public class AccountMenu extends JFrame {
	
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JPanel p4=new JPanel();
    

    JLabel lIBAN=new JLabel("IBAN");
    JLabel lBalance=new JLabel("Balance");
    JLabel lBalanceLimit=new JLabel("BalanceLimit");
    JLabel lTransactionLimit=new JLabel("TransactionLimit");
    JLabel lName = new JLabel("Name");
    JLabel lCnic = new JLabel("CNIC");
    JLabel lGender = new JLabel("Gender");
    JLabel lPassword = new JLabel("Password");
    JLabel lAType = new JLabel("Account Type");
    
    JTextField tfIBAN=new JTextField(8);
    JTextField tfBalance=new JTextField(8);
    JTextField tfBalanceLimit=new JTextField(8);
    JTextField tfTransactionLimit=new JTextField(8);
    JTextField tfName=new JTextField(8);
    JTextField tfCNIC=new JTextField(8);
    JTextField tfPass=new JTextField(8);
    
    JButton add =new JButton("Add");
    JButton remove =new JButton("Remove");
    JButton search =new JButton("Search");
    JButton back =new JButton("Back");
    
    ButtonGroup gAType=new ButtonGroup();    
    JRadioButton current=new JRadioButton("Current");
    JRadioButton saving=new JRadioButton("Saving");
    ButtonGroup gGType=new ButtonGroup();    
    JRadioButton male=new JRadioButton("Male");
    JRadioButton female=new JRadioButton("Female");
    

    public AccountMenu(){
    	
    	File f = new File("acc.dat");
    	Filing aFile = new Filing(f);
    	
        setLayout(new BorderLayout(3,5));
        p1.setLayout(new GridLayout(4,2));
        p2.setLayout(new GridLayout(4,2));
        p3.setLayout(new GridLayout(2,4));
        p4.setLayout(new GridLayout(2,2));
        
        setLayout(new GridLayout(5,5));
        
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
            	dispose();
            	EmployeeMenu f = new EmployeeMenu();
            	MainGUI.goToFrame(f);
            }
        });
        
        
        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
            	if(MainGUI.nameCheck(tfName) && MainGUI.digitCheck(tfCNIC,13,13) && MainGUI.alphanumericCheck(tfIBAN, 11) &&  MainGUI.balanceCheck(tfBalanceLimit, 500, 5000000) && MainGUI.balanceCheck(tfBalance,500, Double.parseDouble(tfBalanceLimit.getText())) && MainGUI.balanceCheck(tfTransactionLimit, 500, Double.parseDouble(tfBalanceLimit.getText())) && MainGUI.radioCheck(lGender, female, male) && MainGUI.radioCheck(lAType, current, saving)) {
            		try {
						if(aFile.searchAccount(tfIBAN.getText())!=null) {
							tfIBAN.setText("IBAN Already Exists");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
            		String gender = null;
            		String accountType = null;
                	if(male.isSelected()==true) {
                		gender = "male";
                	}
                	else if(female.isSelected()==true) {
                		gender = "female";
                	}
                	if(current.isSelected()==true) {
                		accountType = "current";
                	}
                	else if(saving.isSelected()==true) {
                		accountType = "saving";
                	}
                	
                	if(current.isSelected()==true) {
                		CurrentAccount a = new CurrentAccount(tfIBAN.getText(), Double.parseDouble(tfBalance.getText()), Double.parseDouble(tfBalanceLimit.getText()), Double.parseDouble(tfTransactionLimit.getText()), accountType ,(Double.parseDouble(tfBalanceLimit.getText())/0.1));
                		Customer c = new Customer(tfName.getText(), tfCNIC.getText() ,gender, a, tfPass.getText());
                		try {
    						aFile.AddAccount(c);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                	}
                	else if(saving.isSelected()==true){
                		SavingAccount a = new SavingAccount(tfIBAN.getText(), Double.parseDouble(tfBalance.getText()), Double.parseDouble(tfBalanceLimit.getText()), Double.parseDouble(tfTransactionLimit.getText()), accountType ,(Float.parseFloat(tfBalanceLimit.getText())/100*10));
                		Customer c = new Customer(tfName.getText(), tfCNIC.getText() ,gender, a, tfPass.getText());
                		try {
    						aFile.AddAccount(c);
    						dispose();
    						EmployeeMenu f1 = new EmployeeMenu();
    						MainGUI.goToFrame(f1);
    						f1.message.setText("Account Added");
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                	}
            	}
            	
            }

        });
        p3.add(add);
        
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	try {
					if(aFile.deleteAccount(tfIBAN.getText())) {
						dispose();
						EmployeeMenu f = new EmployeeMenu();
						MainGUI.goToFrame(f);
						f.message.setText("Account removed");
					}
					else {
						tfName.setText("No Such record found");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        p3.add(remove);
        
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    Customer a = aFile.searchAccount(tfIBAN.getText());
                    if (a != null) {
                        tfName.setText(a.getName());
                        tfCNIC.setText(a.getCnic());
                        tfIBAN.setText(a.getAccount().getIBAN());
                        tfBalance.setText(String.valueOf(a.getAccount().getBalance()));
                        tfBalanceLimit.setText(String.valueOf(a.getAccount().getBalanceLimit()));
                        tfTransactionLimit.setText(String.valueOf(a.getAccount().getTransactionLimit()));
                        if(a.getGender().equals("male")) {
                        	male.setSelected(true);
                        }
                        else {
                        	female.setSelected(true);
                        }
                        if(a.getAccount().getaccountType().equals("current")) {
                        	current.setSelected(true);
                        }
                        else {
                        	saving.setSelected(true);
                        }
                    }
                    else {
                    	tfName.setText("No such Account found");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                
                
            }
        });
        
        p3.add(search);
        
        gAType.add(saving);
        gAType.add(current);
        
        gGType.add(male);
        gGType.add(female);
        
        

        p1.add(lName);
        p1.add(tfName);
        p1.add(lCnic);
        p1.add(tfCNIC);
        p1.add(lPassword);
        p1.add(tfPass);
        p1.add(lIBAN);
        p1.add(tfIBAN);
        
        p2.add(lBalance);
        p2.add(tfBalance);
        p2.add(lBalanceLimit);
        p2.add(tfBalanceLimit);
        p2.add(lTransactionLimit);
        p2.add(tfTransactionLimit);
        
        p3.add(lGender);
        p3.add(male);
        p3.add(female);
        p3.add(lAType);
        p3.add(saving);
        p3.add(current);

        p4.add(add);
        p4.add(remove);
        p4.add(search);
        p4.add(back);
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
        add(p4, BorderLayout.SOUTH);
          
}
}


