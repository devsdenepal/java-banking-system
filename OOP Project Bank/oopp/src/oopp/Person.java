package oopp;

import java.io.Serializable;

public class Person implements Serializable{
	
	    private String cnic;
	    private String name;
	    private String gender;
	    private String password;
	    
	    public Person(){
	        cnic=null;
	        name=null;
	        gender=null;
	        password = null;
	        
	    }
	    public Person(String cnic,String name,String gender, String password){
	        this.cnic=cnic;
	        this.name=name;
	        this.gender=gender;
	        this.password = password;
	    }

	    public String getCnic() {
	        return cnic;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setCnic(String cnic) {
	        this.cnic = cnic;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }
	    
	    public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    

	    @Override
	    public String toString() {
	        return (" CNIC:"+cnic+" Name: "+name+" Gender: "+ gender);
	    }
		

}
