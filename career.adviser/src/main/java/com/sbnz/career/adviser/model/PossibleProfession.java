package com.sbnz.career.adviser.model;

public class PossibleProfession {

	Profession profession;
	Double score;
	Long numTraits;
	Long numPref;
	
	public PossibleProfession() {
		
	}
	
	public PossibleProfession(Profession profession, Double score, Long numTraits, Long numPref) {
		super();
		this.profession = profession;
		this.score = score;
		this.numTraits = numTraits;
		this.numPref = numPref;
	}
	
	public PossibleProfession(Profession profession, Long numTraits) {
		this.profession = profession;
		this.numTraits=numTraits;
		this.score = 0d;
		this.numPref=0l;
	}

	public PossibleProfession(Profession profession) {
		this.profession = profession;
		this.score = 0d; 
		this.numPref = 0l;
		this.numTraits = 0l;
	}
	
	public void increaseScore(Double number) {
		this.score+=number;
	}


	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Long getNumTraits() {
		return numTraits;
	}

	public void setNumTraits(Long numTraits) {
		this.numTraits = numTraits;
	}

	public Long getNumPref() {
		return numPref;
	}

	public void setNumPref(Long numPref) {
		this.numPref = numPref;
	}
	
	
	
}
