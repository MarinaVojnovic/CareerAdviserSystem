package com.sbnz.career.adviser.dto;

import com.sbnz.career.adviser.entity.TestsDoneInDay;

public class TestsDoneInDayDto {

	Long id;
	String date;
	Long number;
	
	public TestsDoneInDayDto() {
		
	}
	
	public TestsDoneInDayDto(TestsDoneInDay t) {
		this.id=t.getId();
		this.date=t.getDate().toString();
		this.number=t.getNumber();
	}
	public TestsDoneInDayDto(Long id, String date, Long number) {
		super();
		this.id = id;
		this.date = date;
		this.number = number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
	
}
