package com.sbnz.career.adviser.model;

import java.util.Set;

public class Activity {

	Long id;
	String description;
	Set<Profession> professions;
	Boolean isActive;
	
	public Activity() {
		
	}

	public Activity(Long id, String description, Set<Profession> professions, Boolean isActive) {
		super();
		this.id = id;
		this.description = description;
		this.professions = professions;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
