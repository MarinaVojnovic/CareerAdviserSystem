package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.Preference;


public interface PreferenceRespository extends JpaRepository<Preference, Long>{

	@Query(value = "select * from activities activity where activity.is_active = true;", nativeQuery = true)
	List<Preference> getAllActive();
}
