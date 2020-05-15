package com.sbnz.career.adviser.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(catalog = "db_career_adviser", name = "preferences")
public class Preference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String description;
	
	Boolean isActive;
	
	public Preference() {
		
	}

	public Preference(Long id, String description, Boolean isActive) {
		super();
		this.id = id;
		this.description = description;
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



	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
