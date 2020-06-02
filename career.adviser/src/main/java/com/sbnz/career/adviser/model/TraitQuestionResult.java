package com.sbnz.career.adviser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sbnz.career.adviser.entity.TraitQuestion;


public class TraitQuestionResult {


	TraitQuestion traitQuestion;
	
	Boolean isChecked;
	
	public TraitQuestionResult() {
		this.isChecked = false;
	}


	public TraitQuestionResult(TraitQuestion traitQuestion, Boolean isChecked) {
		super();
		this.traitQuestion = traitQuestion;
		this.isChecked = isChecked;
	}
	
	public TraitQuestionResult(TraitQuestion traitQuestion) {
		super();
		this.traitQuestion = traitQuestion;
		this.isChecked = false;
	}

	public TraitQuestion getTraitQuestion() {
		return traitQuestion;
	}

	public void setTraitQuestion(TraitQuestion traitQuestion) {
		this.traitQuestion = traitQuestion;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}





	
	
	
	
	
}
