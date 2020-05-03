package com.sbnz.career.adviser.model;

public class TraitQuestion {

	String text;
	Trait trait;
	
	public TraitQuestion() {
		
	}

	public TraitQuestion(String text, Trait trait) {
		super();
		this.text = text;
		this.trait = trait;
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

	
	
	
}
