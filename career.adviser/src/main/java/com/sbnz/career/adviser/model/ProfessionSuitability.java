package com.sbnz.career.adviser.model;

import com.sbnz.career.adviser.entity.Profession;

public class ProfessionSuitability {

	Profession profession;
	Matching matching;
	
	public ProfessionSuitability() {
		
	}

	public ProfessionSuitability(Profession profession, Matching matching) {
		super();
		this.profession = profession;
		this.matching = matching;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public Matching getMatching() {
		return matching;
	}

	public void setMatching(Matching matching) {
		this.matching = matching;
	}
	
	
}
