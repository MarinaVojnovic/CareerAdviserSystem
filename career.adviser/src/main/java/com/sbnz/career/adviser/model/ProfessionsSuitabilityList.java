package com.sbnz.career.adviser.model;

import java.util.ArrayList;
import java.util.List;

public class ProfessionsSuitabilityList {

	List<ProfessionSuitability> suitableProfessions;
	
	public ProfessionsSuitabilityList() {
		suitableProfessions = new ArrayList<ProfessionSuitability>();
	}

	public ProfessionsSuitabilityList(List<ProfessionSuitability> suitableProfessions) {
		super();
		this.suitableProfessions = suitableProfessions;
	}

	public List<ProfessionSuitability> getSuitableProfessions() {
		return suitableProfessions;
	}

	public void setSuitableProfessions(List<ProfessionSuitability> suitableProfessions) {
		this.suitableProfessions = suitableProfessions;
	}
	
	
}
