package com.sbnz.career.adviser.entity;

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
	Long id;
	
	Date date;
	
	Integer number;
	
	public TestsDoneInDay() {
		
	}
	
	public TestsDoneInDay(Long id, Integer number) {
		super();
		this.id = id;
		this.number = number;
	}
	
	public TestsDoneInDay(Long id, Date date, Integer number) {
		super();
		this.id = id;
		this.date = date;
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
