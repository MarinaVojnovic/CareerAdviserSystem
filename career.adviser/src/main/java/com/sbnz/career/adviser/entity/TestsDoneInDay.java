package com.sbnz.career.adviser.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "db_career_adviser", name = "tests_done_in_a_day")
public class TestsDoneInDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	LocalDate testDate;
	
	Long number;
	
	public TestsDoneInDay() {
		
	}
	
	public TestsDoneInDay(long id, Long number) {
		super();
		this.id = id;
		this.number = number;
	}
	
	public TestsDoneInDay(long id,LocalDate localDate, Long number) {
		super();
		this.id = id;
		this.testDate= localDate;
		this.number = number;
	}
	public TestsDoneInDay(LocalDate localDate) {
		super();
		this.testDate= localDate;
		this.number = 0l;
	}

	public void increase() {
		this.number++;
	}
	public LocalDate getDate() {
		return testDate;
	}

	public void setDate(LocalDate date) {
		this.testDate = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
