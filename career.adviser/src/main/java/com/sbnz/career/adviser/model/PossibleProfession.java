package com.sbnz.career.adviser.model;

public class PossibleProfession {

	Profession profession;
	Integer score;
	
	public PossibleProfession() {
		
	}
	
	public PossibleProfession(Profession profession) {
		this.profession = profession;
		this.score = 0; 
	}

	public PossibleProfession(Profession profession, Integer score) {
		super();
		this.profession = profession;
		this.score = score;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
}
