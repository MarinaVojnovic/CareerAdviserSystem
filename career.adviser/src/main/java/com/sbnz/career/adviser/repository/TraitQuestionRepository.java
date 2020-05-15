package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.TraitQuestion;

public interface TraitQuestionRepository extends JpaRepository<TraitQuestion, Long> {

	
	@Query(value = "select * from trait_questions quest where quest.is_active = true;", nativeQuery = true)
	List<TraitQuestion> getAllActive();
}
