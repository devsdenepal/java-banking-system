package oopp;

import java.io.Serializable;

public class Datee implements Serializable{
	private int days;
	private int months;
	private int years;
	
    public Datee(){
    	this.days = 0;
    	this.months = 0;
    	this.years = 0;
    }
    
    public Datee(int days, int months, int years){
    	this.days = days;
    	this.months = months;
    	this.years = years;
    }


    public int getdays() {
        return days;
    }

    public int getmonths() {
        return months;
    }

    public int getyears() {
        return years;
    }

    @Override
    public String toString() {
        return (" "+Integer.toString(days)+"/"+Integer.toString(months)+"/"+Integer.toString(years));
    }
}
