package com.sbnz.career.adviser.model;

public class PreferenceQuestionResult {

	Long id;
	Long userId;
	Activity activity;
	Boolean isChecked;
	
	public PreferenceQuestionResult() {
		
	}

	public PreferenceQuestionResult(Long id, Long userId, Activity activity, Boolean isChecked) {
		super();
		this.id = id;
		this.userId = userId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
