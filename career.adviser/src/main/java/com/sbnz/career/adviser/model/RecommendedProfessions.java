package com.sbnz.career.adviser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;

public class RecommendedProfessions {


	LinkedHashSet<PossibleProfession> professions;
	


	public RecommendedProfessions(LinkedHashSet<PossibleProfession> professions) {
		super();
		this.professions = professions;
	}
	
	public RecommendedProfessions() {
		this.professions =  new LinkedHashSet<PossibleProfession>();
	}

	public LinkedHashSet<PossibleProfession> getProfessions() {
		return professions;
	}

	public void setProfessions(LinkedHashSet<PossibleProfession> professions) {
		this.professions = professions;
	}
	
	
}
