package oopp;

import java.io.Serializable;

public class Employee  extends Person implements Serializable{
	
	private String ssn;
    private double salary;
    private Boolean onduty;
    private Datee doj; 
    
    public Employee(){
    	super();
        this.ssn=null;
        this.salary=0;
        this.onduty=null;
        this.doj = null;
        
    }
    public Employee(String name, String cnic, String gender, String ssn, double salary, Boolean onduty, Datee doj, String password){
    	super(cnic, name, gender, password);
        this.ssn=ssn;
        this.salary=salary;
        this.onduty=onduty;
        this.doj = doj;
        
    }

   
    public String getSsn() {
		return ssn;
	}
    
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Boolean getOnduty() {
		return onduty;
	}
	
	public Datee getDoj() {
		return doj;
	}
	
	public void setDoj(Datee doj) {
		this.doj = doj;
	}
	
	
	private String duty() {
		String ans = "No";
		if(this.onduty==true) {
			ans = "Yes";
		}
		return ans;
	}
	
	@Override
	public String toString() {
		
		return(super.toString()+" SSN:" + ssn +" Salaray: "+salary+" On Duty"+ this.duty()+ doj.toString() );
	}
	
}
