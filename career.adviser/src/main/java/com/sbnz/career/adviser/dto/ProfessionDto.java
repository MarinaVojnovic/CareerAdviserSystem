package com.sbnz.career.adviser.dto;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;

public class ProfessionDto {

	
	Long id;
	
	String name;

	List<Preference> activities;
	
	List<Trait> traits;
	
	String description;
	
	Boolean isActive;
	
	Integer payment;
	
	Integer employment;
	
	String image;
	
	Double employmentScore;
	
	public ProfessionDto() {
		
	}
	
	public ProfessionDto(Long id, String name, List<Preference> activities, List<Trait> traits,
			String description, Boolean isActive, Integer payment, Integer employment, String image) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
		this.image=image;
	}

	public ProfessionDto(Long id, String name, List<Preference> activities, List<Trait> traits,
			String description, Boolean isActive, Integer payment, Integer employment, String image, Double employmentScore) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
		this.image=image;
		this.employmentScore=employmentScore;
	}
	
	public ProfessionDto(Profession profession) {
		this.id=profession.getId();
		this.name=profession.getName();
		//this.activities=profession.getActivities();
		//this.traits=profession.getTraits();
		this.isActive=profession.getIsActive();
		this.payment=profession.getPayment();
		this.employment=profession.getEmployment();
		this.image=profession.getImage();
		
		List<Trait> list = new ArrayList<Trait>();
		for (Trait t : profession.getTraits()) {
			list.add(t);
		}
		
		List<Preference> preferencesList = new ArrayList<Preference>();
		for (Preference p : profession.getActivities()) {
			preferencesList.add(p);
		}
		this.activities=preferencesList;
		this.traits=list;
		this.employmentScore = profession.getEmploymentScore();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Preference> getActivities() {
		return activities;
	}

	public void setActivities(List<Preference> activities) {
		this.activities = activities;
	}

	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getEmployment() {
		return employment;
	}

	public void setEmployment(Integer employment) {
		this.employment = employment;
	}

	public Double getEmploymentScore() {
		return employmentScore;
	}

	public void setEmploymentScore(Double employmentScore) {
		this.employmentScore = employmentScore;
	}
	
	
	
	
}
