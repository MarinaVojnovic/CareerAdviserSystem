package com.sbnz.career.adviser.dto;

import java.util.Set;


import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;

public class ProfessionDto {

	
	Long id;
	
	String name;

	Set<Preference> activities;
	
	Set<Trait> traits;

	ProfessionalField field;
	
	String description;
	
	Boolean isActive;
	
	Integer payment;
	
	Integer employment;
	
	public ProfessionDto() {
		
	}

	public ProfessionDto(Long id, String name, Set<Preference> activities, Set<Trait> traits, ProfessionalField field,
			String description, Boolean isActive, Integer payment, Integer employment) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.field = field;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
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

	public Set<Preference> getActivities() {
		return activities;
	}

	public void setActivities(Set<Preference> activities) {
		this.activities = activities;
	}

	public Set<Trait> getTraits() {
		return traits;
	}

	public void setTraits(Set<Trait> traits) {
		this.traits = traits;
	}

	public ProfessionalField getField() {
		return field;
	}

	public void setField(ProfessionalField field) {
		this.field = field;
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
	
	
}
