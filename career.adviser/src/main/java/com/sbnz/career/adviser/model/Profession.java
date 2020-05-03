package com.sbnz.career.adviser.model;

import java.util.Set;

import com.sbnz.career.enums.ProfessionalField;

public class Profession {

	Long id;
	String name;
	Set<Activity> activities;
	Set<Trait> traits;
	ProfessionalField field;
	String description;
	Boolean isActive;
	
	public Profession() {
		
	}
	
	public Profession(Long id, String name, Set<Activity> activities, Set<Trait> traits, ProfessionalField field,
			String description, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.field = field;
		this.description = description;
		this.isActive = isActive;
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

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
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
	
	
	
}
