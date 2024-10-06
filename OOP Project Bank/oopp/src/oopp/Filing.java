package oopp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Filing {
	
	static File f;
	ArrayList<Account> accounts=new ArrayList<Account>();
	ArrayList<Employee> employees=new ArrayList<Employee>();
	  
    public Filing(File f) {
   	 this.f = f;
    }
    
    public String deleteEmployee(String cnic) throws IOException, ClassNotFoundException{
     	ArrayList<Employee> outemployees = new ArrayList<>();
     	if(f.exists()) {
     		try {
     			ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
    	 		outemployees = (ArrayList<Employee>) i.readObject();
    	 		i.close();
     		} catch(Exception ex) {
     			
     		}
     	}
     	String action="";
     	Boolean found = false;
     	for(int i =0; i<outemployees.size();i++) {
    		if(outemployees.get(i).getCnic().equals(cnic)) {
    			outemployees.remove(i);
    			found = true;
    			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
     			o.writeObject(outemployees);
     			o.close();
     			action = "Record Deleted";
     			break;
    		}
    	}
     	if(!found) {
     		action = "No such record found";
     	}
     	return action;
     }
    
    public Employee searchEmployee(String cnic) throws IOException, ClassNotFoundException{
     	ArrayList<Employee> outemployees = new ArrayList<>();
     	Employee emp = new Employee();
     	emp =null;
     	if(f.exists()) {
     		try {
     			ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
    	 		outemployees = (ArrayList<Employee>) i.readObject();
    	 		i.close();
     		} catch(Exception e) {
     			
     		}
     		
     	}
     	Boolean searched = false;
     	for(int i =0; i<outemployees.size();i++) {
    		if(outemployees.get(i).getCnic().equals(cnic)) {
    			emp = outemployees.get(i);
    			searched = true;
    		}
    	}
     	if(searched) {
     	 	System.out.println("Record found");
     	}
     	else {
     		System.out.println("No such record found");
     	}
     	return emp;
     	
    }

    public String displayEmployee() throws IOException, ClassNotFoundException, NullPointerException{
	    ArrayList<Employee> outemployees = new ArrayList();
	    String list = "";
		if(f.exists()) {
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
			outemployees = (ArrayList) i.readObject();
			i.close();
		}
		for(int i = 0; i<outemployees.size();i++) {
			String s="";
			list+="\n Employee No: ";
			list+=s.valueOf(i+1);
			list+= outemployees.get(i).toString();
		}
		return list;
	}   
    
    
    public void AddEmployee(Employee e) throws IOException, ClassNotFoundException{
     	ArrayList<Employee> outemployees = new ArrayList<>();
     	if(f.exists()) {
     		try {
     			ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
     	 		outemployees = (ArrayList<Employee>) i.readObject();
     	 		i.close();
     		} catch(Exception ex) {
     			
     		}
     	}
     	outemployees.add(e);
     	try {
     		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
    	 	o.writeObject(outemployees);
    	 	o.close();
     	}  catch(Exception ex) {
     		
     	}
     	
     }
	public void deposit(String IBAN, double balance){
	    ArrayList<Customer> outaccounts = new ArrayList<>();
	    if(f.exists()) {
	        try {
	            ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
	            outaccounts = (ArrayList<Customer>) i.readObject();
	            i.close();
	        } catch(Exception e) {
	        }
	    }
	    for(int i =0; i<outaccounts.size();i++) {
	        if(outaccounts.get(i).getAccount().getIBAN().equals(IBAN)) {
	                outaccounts.get(i).getAccount().deposit(balance);
	        }
	    }
	    try {
	        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
	        o.writeObject(outaccounts);
	        o.close();
	    } catch (Exception e) {
	        
	    }
	}


	public void transfer(String senderIBAN, String recieverIBAN, double balance){
	    ArrayList<Customer> outaccounts = new ArrayList<>();
	    if(f.exists()) {
	        try {
	            ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
	            outaccounts = (ArrayList<Customer>) i.readObject();
	            i.close();
	        } catch(Exception e) {
	        }
	    }
	    for(int i =0;i<outaccounts.size();i++) {
	        if(outaccounts.get(i).getAccount().getIBAN().equals(senderIBAN)) {
	            outaccounts.get(i).getAccount().withdraw(balance);
	        }
	    }
	    for(int i =0;i<outaccounts.size();i++) {
	        if(outaccounts.get(i).getAccount().getIBAN().equals(recieverIBAN)) {
	            outaccounts.get(i).getAccount().deposit(balance);
	        }
	    }
	    try {
	        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
	        o.writeObject(outaccounts);
	        o.close();
	    } catch (Exception e) {
	
	    }
	}

	public void withdraw(String IBAN, double balance){
	    ArrayList<Customer> outaccounts = new ArrayList<>();
	    if(f.exists()) {
	        try {
	            ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
	            outaccounts = (ArrayList<Customer>) i.readObject();
	            i.close();
	        } catch(Exception e) {
	        }
	    }
	    for(int i =0;i<outaccounts.size();i++) {
	        if(outaccounts.get(i).getAccount().getIBAN().equals(IBAN)) {
	            outaccounts.get(i).getAccount().withdraw(balance);
	        }
	    }
	    try {
	        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
	        o.writeObject(outaccounts);
	        o.close();
	    } catch (Exception e) {
	
	    }
	}

	public double viewBalance(String IBAN){
	    ArrayList<Customer> outaccounts = new ArrayList<>();
		if(f.exists()) {
	            try {
	                ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
	                outaccounts = (ArrayList<Customer>) i.readObject();
	                i.close();
	            } catch(Exception e) {
	            }	
		}
	        double amount = 0;
		for(int i =0; i<outaccounts.size();i++) {
	            if(outaccounts.get(i).getAccount().getIBAN().equals(IBAN)) {
	                    amount = outaccounts.get(i).getAccount().getBalance();
	            }
		}
	        return amount;
	}

	public void AddAccount(Customer a) throws IOException, ClassNotFoundException{
		ArrayList<Customer> outaccounts = new ArrayList<>();
		if(f.exists()) {
			try {
				ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
		 		outaccounts = (ArrayList<Customer>) i.readObject();
		 		i.close();
			} catch(Exception ex) {
				
			}
		}
		outaccounts.add(a);
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
		 	o.writeObject(outaccounts);
		 	o.close();
		 	System.out.println("Done!!!!!");
		}  catch(Exception e) {
			
		}
		
	}

 


	public boolean deleteAccount(String IBAN) throws IOException, ClassNotFoundException{
		ArrayList<Customer> outaccounts = new ArrayList<>();
		if(f.exists()) {
			try {
				ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
		 		outaccounts = (ArrayList<Customer>) i.readObject();
		 		i.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Customer a = new Customer();
		for(int i =0; i<outaccounts.size();i++) {
			if(outaccounts.get(i).getAccount().getIBAN().equals(IBAN)) {
				System.out.println(outaccounts.get(i).toString());
				a = outaccounts.get(i);
				return true;
			}
		}
		
		 	return false;
		}

	public Customer searchAccount(String IBAN) throws IOException, ClassNotFoundException{
		ArrayList<Customer> outaccounts = new ArrayList<Customer>();
	    Customer a = new Customer();
	    a = null;
		if(f.exists()) {
			try {
				ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
		 		outaccounts = (ArrayList<Customer>) i.readObject();
		 		i.close();
			} catch(Exception e) {
				
			}
			
		}
		Boolean searched = false;
		for(int i =0; i<outaccounts.size();i++) {
			if(outaccounts.get(i).getAccount().getIBAN().equals(IBAN)) {
				a = outaccounts.get(i);
				searched = true;
			}
		}
		if(searched) {
		 	return a;
		}
	        return null;
		
	}


}
