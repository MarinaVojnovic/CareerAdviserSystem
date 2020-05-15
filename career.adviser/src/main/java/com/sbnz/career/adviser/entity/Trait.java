package com.sbnz.career.adviser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "db_career_adviser", name = "traits")
public class Trait {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String personalityField;
	String target;
	
	public Trait() {
		
		
	}
	public Trait(Long id, String personalityField, String target) {
		super();
		this.personalityField = personalityField;
		this.target = target;
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
