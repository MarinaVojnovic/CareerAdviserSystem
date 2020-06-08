package com.sbnz.career.adviser.model;

public class Counter {

	Integer number;
	
	public Counter() {
		this.number=0;
	}

	public Counter(Integer number) {
		super();
		this.number = number;
	}
	
	public void increase() {
		this.number++;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
