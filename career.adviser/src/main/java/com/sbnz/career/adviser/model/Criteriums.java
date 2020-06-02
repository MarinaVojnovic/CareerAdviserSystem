package com.sbnz.career.adviser.model;

public class Criteriums {
	
	Boolean personality;
	Boolean preferences;
	Boolean payment;
	Boolean employment;
	
	public Criteriums() {
		
	}
	
	public Criteriums(Boolean personality, Boolean preferences, Boolean payment, Boolean employment) {
		super();
		this.personality = personality;
		this.preferences = preferences;
		this.payment = payment;
		this.employment = employment;
	}
	
	
	public Boolean getPersonality() {
		return personality;
	}
	public void setPersonality(Boolean personality) {
		this.personality = personality;
	}
	public Boolean getPreferences() {
		return preferences;
	}
	public void setPreferences(Boolean preferences) {
		this.preferences = preferences;
	}
	public Boolean getPayment() {
		return payment;
	}
	public void setPayment(Boolean payment) {
		this.payment = payment;
	}
	public Boolean getEmployment() {
		return employment;
	}
	public void setEmployment(Boolean employment) {
		this.employment = employment;
	}
	
	

}
