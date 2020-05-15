package com.sbnz.career.adviser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sbnz.career.adviser.dto.TraitQuestionDto;

@Entity
@Table(catalog = "db_career_adviser", name = "trait_questions")
public class TraitQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String text;
	
	@ManyToOne
	Trait trait;
	
	Boolean isActive;
	
	public TraitQuestion() {
		
	}
	
	

	public TraitQuestion(Long id, String text, Trait trait, Boolean isActive) {
		super();
		this.id = id;
		this.text = text;
		this.trait = trait;
		this.isActive = isActive;
	}



	public TraitQuestion(String text, Trait trait, Boolean isActive) {
		super();
		this.text = text;
		this.trait = trait;
		this.isActive = isActive;
	}
	
	public TraitQuestion(Trait trait, String text) {
		this.trait=trait;
		this.text=text;
		this.isActive=true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Trait getTrait() {
		return trait;
	}

	public void setTrait(Trait trait) {
		this.trait = trait;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

	
	
	
}
