package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.dto.PreferenceDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.model.RecommendedProfessions;

public interface PreferenceService {

	List<Preference> getAllActive();
	Preference findById(Long id);
	List<Preference> getAll();
	void create(PreferenceDto prefDto);
	void delete(Preference preference);
	void activate(Preference preference);
	void update(Long prefId, PreferenceDto prefDto);
	List<Preference> findByField(ProfessionalField field);
	
}
