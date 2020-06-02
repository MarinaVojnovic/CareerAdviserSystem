package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.TraitQuestionResult;

public interface TraitQuestionService {
	List<TraitQuestion> getAllActive();
	TraitQuestion create(TraitQuestion question);
	void delete(TraitQuestion traitQuestion);
	TraitQuestion findById(Long id);
	void update(Long questId, TraitQuestionDto questDto);
	List<TraitQuestion> getAll();
	void activate(TraitQuestion traitQuestion);

}
