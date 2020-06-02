package com.sbnz.career.adviser.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sbnz.career.adviser.dto.PreferenceDto;


@Entity
@Table(catalog = "db_career_adviser", name = "preferences")
public class Preference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String description;
	
	Boolean isActive;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "field_id")
	ProfessionalField field;
	
	public Preference() {
		
	}

	public Preference(Long id, String description, Boolean isActive, ProfessionalField field) {
		super();
		this.id = id;
		this.description = description;
		this.isActive = isActive;
		this.field=field;
	}
	
	public Preference(PreferenceDto prefDto) {
		this.id=prefDto.getId();
		this.description=prefDto.getDescription();
		this.isActive=prefDto.getIsActive();
		this.field=prefDto.getField();
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

	public ProfessionalField getField() {
		return field;
	}

	public void setField(ProfessionalField field) {
		this.field = field;
	}
	
	
	
	
	
}
