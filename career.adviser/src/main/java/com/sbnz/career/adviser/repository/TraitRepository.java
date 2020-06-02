package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.Trait;

public interface TraitRepository extends JpaRepository<Trait, Long>{

	@Query(value = "select * from traits", nativeQuery = true)
	List<Trait> getAll();
	Trait findByTarget(String target);
	@Query(value = "select * from traits where personality_field=?1", nativeQuery = true)
	List<Trait> getByPersonalityField(String personalityField);
}
