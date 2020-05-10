package com.sbnz.career.adviser.model;

import java.util.ArrayList;
import java.util.List;

public class Matching {

	List<Trait> traits;
	List<Activity> preferences;
	
	public Matching() {
		traits = new ArrayList<Trait>();
		preferences = new ArrayList<Activity>();
	}

	public Matching(List<Trait> traits, List<Activity> preferences) {
		super();
		this.traits = traits;
		this.preferences = preferences;
	}

	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}

	public List<Activity> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Activity> preferences) {
		this.preferences = preferences;
	}
	
	
}
