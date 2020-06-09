package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.TestsDoneInDay;

public interface TestsDoneInDayRepository extends JpaRepository<TestsDoneInDay, Long>{

}
