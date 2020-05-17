package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.ProfessionalField;

public interface ProfessionalFieldRepository extends JpaRepository<ProfessionalField, Long>{

	ProfessionalField findByName(String name);
}
