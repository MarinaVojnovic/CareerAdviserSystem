package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.ProfessionalField;

public interface ProfessionalFieldService {

	 List<ProfessionalField> getAllFields();
	 void create(ProfessionalField professionalField);
}
