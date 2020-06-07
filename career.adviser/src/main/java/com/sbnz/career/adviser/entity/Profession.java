package com.sbnz.career.adviser.entity;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialException;

import com.sbnz.career.adviser.dto.ProfessionDto;



@Entity
@Table(catalog = "db_career_adviser", name = "professions")
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@Column(name="name")
	String name;

	@ManyToMany( fetch = FetchType.EAGER)
	Set<Preference> activities;
	
	@ManyToMany
	Set<Trait> traits;
	
	@Lob
	@Column(name = "description")
	Clob description;
	
	@Column(name="is_active")
	Boolean isActive;
	
	@Column(name="payment")
	Integer payment;
	
	@Column(name="employment")
	Integer employment;
	
	@Column(name="employment_score")
	Double employmentScore;
	
	@Column(name="image")
	String image;
	
	public Profession(Long id, String name, Set<Preference> activities, Set<Trait> traits,
			Clob description, Boolean isActive, Integer payment, Integer employment, String image) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
		this.image=image;
	}
	
	public Profession(Long id, String name, Set<Preference> activities, Set<Trait> traits,
			Clob description, Boolean isActive, Integer payment, Integer employment, String image, Double employmentScore) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
		this.description = description;
		this.isActive = isActive;
		this.payment = payment;
		this.employment = employment;
		this.image=image;
		this.employmentScore = employmentScore;
	}
	
	public Profession(ProfessionDto profDto) {
		this.name=profDto.getName();
		//this.activities=profDto.getActivities();
		
		Set<Preference> prefSet = new HashSet<Preference>();
		for (Preference prefer : profDto.getActivities()) {
			prefSet.add(prefer);
		}
		this.activities=prefSet;
		//this.traits=profDto.getTraits();
		Set<Trait> setTraits = new HashSet<Trait>();
		for (Trait trait : profDto.getTraits()) {
			setTraits.add(trait);
		}
		this.traits=setTraits;
		try {
			this.description=new javax.sql.rowset.serial.SerialClob(profDto.getDescription().toCharArray());
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.isActive=profDto.getIsActive();
		this.payment=profDto.getPayment();
		this.employment=profDto.getEmployment();
		this.image = profDto.getImage();
	}

	public Profession() {
		
	}
	
	public Profession(Long id, String name, Set<Preference> activities, Set<Trait> traits, ProfessionalField field,
			Clob description, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.activities = activities;
		this.traits = traits;
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


	public Clob getDescription() {
		return description;
	}

	public void setDescription(Clob description) {
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
	
	public String getImage() {
		return this.image;
	}

	public Double getEmploymentScore() {
		return employmentScore;
	}

	public void setEmploymentScore(Double employmentScore) {
		this.employmentScore = employmentScore;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
