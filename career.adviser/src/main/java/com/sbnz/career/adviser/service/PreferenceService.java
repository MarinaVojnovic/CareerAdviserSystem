package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.model.RecommendedProfessions;

public interface PreferenceService {

	List<Preference> getAllActive();
	
}
