package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;

public interface TraitsResultRepository extends JpaRepository<TraitsResult, Long>{

	TraitsResult findByUser(User user);
}
