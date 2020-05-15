package com.sbnz.career.adviser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(catalog = "db_career_adviser", name = "preference_question_results")
@Entity
public class PreferenceQuestionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	User user;
	
	@OneToOne
	Preference activity;
	
	Boolean isChecked;
	
	public PreferenceQuestionResult() {
		
	}

	public PreferenceQuestionResult(Long id, User user, Preference activity, Boolean isChecked) {
		super();
		this.id = id;
		this.user = user;
		this.activity = activity;
		this.isChecked = isChecked;
	}
	
	public PreferenceQuestionResult(Preference activity) {
		this.activity=activity;
		this.id=null;
		this.user=null;
		this.isChecked=false;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
