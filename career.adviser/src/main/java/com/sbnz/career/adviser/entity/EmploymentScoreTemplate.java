package com.sbnz.career.adviser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "db_career_adviser", name = "employment_score_template")
public class EmploymentScoreTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	private int minPerc;
	private int maxPerc;
	private Double score;
	
	public EmploymentScoreTemplate() {
		
	}

	public EmploymentScoreTemplate(int minPerc, int maxPerc, Double score) {
		super();
		this.minPerc = minPerc;
		this.maxPerc = maxPerc;
		this.score = score;
	}

	public int getMinPerc() {
		return minPerc;
	}

	public void setMinPerc(int minPerc) {
		this.minPerc = minPerc;
	}

	public int getMaxPerc() {
		return maxPerc;
	}

	public void setMaxPerc(int maxPerc) {
		this.maxPerc = maxPerc;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	
}
