package com.sbnz.career.adviser.dto;

import com.sbnz.career.adviser.model.Activity;

public class PreferenceQuestionResultDto {

	Long id;
	Activity activity;
	Boolean isChecked;
	
	public PreferenceQuestionResultDto() {
		
	}

	public PreferenceQuestionResultDto(Long id, Activity activity, Boolean isChecked) {
		super();
		this.id = id;
		this.activity = activity;
		this.isChecked = isChecked;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
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
