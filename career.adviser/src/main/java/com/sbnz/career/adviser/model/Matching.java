package com.sbnz.career.adviser.model;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.Trait;

public class Matching {

	List<Trait> traits;
	List<Preference> preferences;
	
	public Matching() {
		traits = new ArrayList<Trait>();
		preferences = new ArrayList<Preference>();
	}

	public Matching(List<Trait> traits, List<Preference> preferences) {
		super();
		this.traits = traits;
		this.preferences = preferences;
	}

	public void addMatchingPref(List<Object> matchingPreferences) {
		for (Object o : matchingPreferences){
    		Preference activity = (Preference) o;
    		this.preferences.add(activity);
    	}
	}
	
	public void addMatchingTraits(List<Object> matchingTraits) {
		for (Object o : matchingTraits){
    		Trait trait = (Trait) o;
    		this.traits.add(trait);
    	}
	}
	
	
	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}
	
	
}
