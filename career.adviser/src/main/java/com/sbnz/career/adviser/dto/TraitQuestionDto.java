package com.sbnz.career.adviser.dto;

public class TraitQuestionDto {

	Long id;
	String text;
	String personalityField;
	String target;
	Boolean isActive;
	
	public TraitQuestionDto() {
		
	}

	public TraitQuestionDto(String text, String personalityField, String target, Boolean isActive) {
		super();
		this.text = text;
		this.personalityField = personalityField;
		this.target = target;
		this.isActive = isActive;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Boolean isActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
