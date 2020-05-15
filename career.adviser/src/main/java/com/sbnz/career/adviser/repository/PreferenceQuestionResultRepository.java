package com.sbnz.career.adviser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.User;

public interface PreferenceQuestionResultRepository extends JpaRepository<PreferenceQuestionResult, Long>{

	List<PreferenceQuestionResult> findByUser(User user);
}
