package com.sbnz.career.adviser.dto;

public class PreferenceDto {

	Long id;

	String description;
	
	Boolean isActive;
	
	public PreferenceDto() {
		
	}

	public PreferenceDto(Long id, String description, Boolean isActive) {
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
