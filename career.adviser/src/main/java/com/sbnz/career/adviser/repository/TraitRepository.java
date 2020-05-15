package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.Trait;

public interface TraitRepository extends JpaRepository<Trait, Long>{

	Trait findByTarget(String target);
}
