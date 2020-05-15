package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long>{

	@Query(value = "select * from professions prof where prof.is_active = true;", nativeQuery = true)
	List<Profession> getAllActive();
}
