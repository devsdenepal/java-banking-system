package oopp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
public class EmployeeForm extends JFrame {
		
	    String[]days=new String[31];
	    String[]months=new String[12];
	    String[]years=new String[33];
	    
	    JPanel ptf1=new JPanel();
	    JPanel ptf2=new JPanel();
	    JPanel pr=new JPanel();
	    JPanel pco=new JPanel();
	    JPanel pb=new JPanel();
	    
	    JLabel date=new JLabel("Date of Joining");
	    JLabel lCnic=new JLabel("Cnic");
	    JLabel lName=new JLabel("Name");
	    JLabel lGender=new JLabel("Gender");
	    JLabel lSsn=new JLabel("SSN");
	    JLabel lSalary=new JLabel("Salary");
	    JLabel lOnDuty=new JLabel("On Duty");
	    JLabel lPass=new JLabel("Password");
	    
	    JTextField tfName=new JTextField(8);
	    JTextField tfCnic=new JTextField(8);
	    JTextField tfSsn=new JTextField(8);
	    JTextField tfSalary=new JTextField(8);  
	    JTextField tfPass=new JTextField(8);  
	    
	    JRadioButton male =new JRadioButton("Male");
	    JRadioButton female =new JRadioButton("Female");
	    
	    ButtonGroup g=new ButtonGroup();
	    
	    JButton save=new JButton("Save");
	    JButton delete=new JButton("Delete");
	    JButton search=new JButton("Search");
	    JButton back=new JButton("Back");
	    
	    JCheckBox onDuty = new JCheckBox("On Duty");
	    
	    JComboBox<String> ODays;
	    JComboBox<String> OMonths;
	    JComboBox<String> OYears;
	    
	    public EmployeeForm(){
	    	
	        File f = new File("Employees.dat");
	        Filing files = new Filing(f);
	        
	        JFrame frame=new JFrame("Frame");
	        
	        for(int i=1;i<32;i++){
	            days[i-1]=String.valueOf(i);
	        }
	        for(int i=1;i<13;i++){
	            months[i-1]=String.valueOf(i);
	        }
	        int a = 2022;
	        for(int i=1;i<34;i++){
	            years[i-1]=String.valueOf(a);
	            a-=1;
	        }
	        ODays=new JComboBox<>(days);
	        OMonths=new JComboBox<>(months);
	        OYears=new JComboBox<>(years);
	        
	        setLayout(new BorderLayout(3,5));
	        ptf1.setLayout(new GridLayout(3,2));
	        pr.setLayout(new GridLayout(1,3));
	        ptf2.setLayout(new GridLayout(4,2));
	        pb.setLayout(new GridLayout(1,4));
	        pco.setLayout(new GridLayout(1,1));
	        setLayout(new GridLayout(5,5));
	        
	        save.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	if(MainGUI.nameCheck(tfName) && MainGUI.digitCheck(tfSalary,5,6) && MainGUI.digitCheck(tfSsn,9,9) && MainGUI.digitCheck(tfCnic,13,13) && MainGUI.radioCheck(lGender, male, female)) {
	            		try {
							if(files.searchEmployee(tfCnic.getText())!=null) {
								tfCnic.setText("Employee Already Exists");
							}
						} catch (ClassNotFoundException | IOException e2) {
							e2.printStackTrace();
						}
	            		String gend = "";
		                if(male.isSelected()) {
		                	gend = "Male";
		                }
		                else if(female.isSelected()) {
		                	gend = "Female";
		                }
		                
		                Datee d = new Datee(Integer.parseInt(ODays.getSelectedItem().toString()), Integer.parseInt(OMonths.getSelectedItem().toString()), Integer.parseInt(OYears.getSelectedItem().toString()));
		                
		                Employee e = new Employee(tfName.getText(), tfCnic.getText(), gend, tfSsn.getText(), Double.parseDouble(tfSalary.getText()), onDuty.isSelected(), d, tfPass.getText());

		            	try {
							files.AddEmployee(e);
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
		            }
	            }

	        });
	        pb.add(save);
	        
	        delete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	try {
	            		dispose();
		            	ManagerMenu f = new ManagerMenu();
		            	MainGUI.goToFrame(f);
		            	f.list.setText(files.deleteEmployee(tfCnic.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        });
	        pb.add(delete);
	        
	        search.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt){
	            	try {
						Employee e = files.searchEmployee(tfCnic.getText());
						if(e==null) {
							tfCnic.setText("No Such Employee found");
						}
						else {
							tfName.setText(e.getName());
							tfSalary.setText(String.valueOf(e.getSalary()));
							tfSsn.setText(e.getSsn());
							System.out.println(e.getOnduty());
							if(e.getGender().equals("male")) {
								male.setSelected(true);
							}
							else{
								female.setSelected(true);
							}
							onDuty.setSelected(e.getOnduty());
							ODays.setSelectedItem(e.getDoj().getdays());
							OMonths.setSelectedItem(e.getDoj().getmonths());
							OYears.setSelectedItem(e.getDoj().getyears());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        });
	        pb.add(search);
	        
	        back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt){
	            	dispose();
	            	ManagerMenu f = new ManagerMenu();
	            	MainGUI.goToFrame(f);
	                
	            }
	        });
	        pb.add(back);
	        
	        ptf1.add(lName);
	        ptf1.add(tfName);
	        ptf1.add(lCnic);
	        ptf1.add(tfCnic);
	        ptf1.add(lPass);
	        ptf1.add(tfPass);
	        
	        ptf2.add(lSsn);
	        ptf2.add(tfSsn);
	        ptf2.add(lSalary);
	        ptf2.add(tfSalary);
	        ptf2.add(onDuty);
	        
	        pr.add(lGender);
	        pr.add(male);
	        pr.add(female);
	        
	        pco.add(date);
	        pco.add(ODays);
	        pco.add(OMonths);
	        pco.add(OYears);
	        
	        add(ptf1,BorderLayout.NORTH);
	        add(pr,BorderLayout.CENTER);
	        add(ptf2,BorderLayout.EAST);
	        add(pco,BorderLayout.WEST);
	        add(pb,BorderLayout.SOUTH);
	          
	}
}

