package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.ProfessionalField;

public interface ProfessionalFieldRepository extends JpaRepository<ProfessionalField, Long>{

	ProfessionalField findByName(String name);
	
	@Query(value = "select * from professional_fields;", nativeQuery = true)
	List<ProfessionalField> getAll();
	

}
