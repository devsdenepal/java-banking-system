package oopp;

import java.io.Serializable;

public class Customer extends Person implements Serializable{
	
	private Account account;
    
    public Customer(){
    	super();
        this.account=null;
        
    }
    
    public Customer(String name, String cnic, String gender, Account account, String password){
    	super(cnic, name, gender, password);
        this.account=account;
        
    }

   
    public Account getAccount() {
            return account;
	}

	@Override
	public String toString() {
		super.toString();
		return(account.toString());
	}
	
		
	
}
