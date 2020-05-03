package com.sbnz.career.adviser.model;

public class Trait {

	String personalityField;
	String target;
	
	public Trait() {
		
		
	}
	public Trait(String personalityField, String target) {
		super();
		this.personalityField = personalityField;
		this.target = target;
	}
	
	
	public String getPersonalityField() {
		return personalityField;
	}
	public void setPersonalityField(String personalityField) {
		this.personalityField = personalityField;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
	
	
}
