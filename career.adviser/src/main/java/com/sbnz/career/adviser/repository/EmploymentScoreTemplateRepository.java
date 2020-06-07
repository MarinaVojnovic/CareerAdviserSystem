package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.EmploymentScoreTemplate;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;

public interface EmploymentScoreTemplateRepository extends JpaRepository<EmploymentScoreTemplate, Long>{

}
