package com.sbnz.career.adviser.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sbnz.career.adviser.dto.ProfessionDto;



@Entity
@Table(catalog = "db_career_adviser", name = "professions")
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;

	@ManyToMany( fetch = FetchType.EAGER)
	Set<Preference> activities;
	
	@ManyToMany
	Set<Trait> traits;
	
	@ManyToOne
	ProfessionalField field;
	
	String description;
	
	Boolean isActive;
	
	Integer payment;
	
	Integer employment;
	
	public Profession(Long id, String name, Set<Preference> activities, Set<Trait> traits, ProfessionalField field,
			String description, Boolean isActive, Integer payment, Integer employment) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.field = field;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
	}
	
	public Profession(ProfessionDto profDto) {
		this.name=profDto.getName();
		this.activities=profDto.getActivities();
		this.traits=profDto.getTraits();
		this.field=profDto.getField();
		this.description=profDto.getDescription();
		this.isActive=profDto.getIsActive();
		this.payment=profDto.getPayment();
		this.employment=profDto.getEmployment();
	}

	public Profession() {
		
	}
	
	public Profession(Long id, String name, Set<Preference> activities, Set<Trait> traits, ProfessionalField field,
			String description, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.field = field;
		this.description = description;
		this.isActive = isActive;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Preference> getActivities() {
		return activities;
	}

	public void setActivities(Set<Preference> activities) {
		this.activities = activities;
	}

	public Set<Trait> getTraits() {
		return traits;
	}

	public void setTraits(Set<Trait> traits) {
		this.traits = traits;
	}

	public ProfessionalField getField() {
		return field;
	}

	public void setField(ProfessionalField field) {
		this.field = field;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getEmployment() {
		return employment;
	}

	public void setEmployment(Integer employment) {
		this.employment = employment;
	}
	
	
	
}
