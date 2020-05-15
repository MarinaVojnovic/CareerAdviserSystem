package com.sbnz.career.adviser.dto;

import com.sbnz.career.adviser.entity.Preference;

public class PreferenceQuestionResultDto {

	Long id;
	Preference activity;
	Boolean isChecked;
	
	public PreferenceQuestionResultDto() {
		
	}

	public PreferenceQuestionResultDto(Long id, Preference activity, Boolean isChecked) {
		super();
		this.id = id;
		this.activity = activity;
		this.isChecked = isChecked;
	}

	public Preference getActivity() {
		return activity;
	}

	public void setActivity(Preference activity) {
		this.activity = activity;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
