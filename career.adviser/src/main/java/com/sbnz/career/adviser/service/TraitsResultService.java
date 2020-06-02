package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.TraitQuestionResult;


public interface TraitsResultService {

	void submitTraitQuestionResults(List<TraitQuestionResult> traitQuestionResults);
	TraitsResult getTraitResult();
}
