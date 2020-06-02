package com.sbnz.career.adviser.dto;

import com.sbnz.career.adviser.entity.ProfessionalField;

public class PreferenceDto {

	Long id;

	String description;
	
	Boolean isActive;
	
	ProfessionalField field;
	
	public PreferenceDto() {
		
	}

	public PreferenceDto(Long id, String description, Boolean isActive, ProfessionalField field) {
		super();
		this.id = id;
		this.description = description;
		this.isActive = isActive;
		this.field=field;
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
