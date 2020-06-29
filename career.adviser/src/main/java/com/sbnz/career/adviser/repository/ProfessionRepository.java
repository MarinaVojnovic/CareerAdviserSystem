package com.sbnz.career.adviser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sbnz.career.adviser.entity.Profession;

public interface ProfessionRepository extends PagingAndSortingRepository<Profession, Long>{

	@Query(value = "select * from professions prof where prof.is_active = true;", nativeQuery = true)
	List<Profession> getAllActive();
	
	Optional<Profession> findById(Long id);
	
	
	@Query(value = "select * from professions prof where prof.is_active = false;", nativeQuery = true)
	List<Profession> getAllDeleted();
	
	
}


