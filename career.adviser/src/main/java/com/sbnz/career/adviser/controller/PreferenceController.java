package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.PreferenceService;
import com.sbnz.career.adviser.service.TraitQuestionService;

@RestController
@RequestMapping(value = "/preferences")
public class PreferenceController {

	private PreferenceService preferenceService;

	public PreferenceController(PreferenceService preferenceService) {
		this.preferenceService = preferenceService;
	}
	
	@GetMapping(value = "/getAllQuestionsForUser")
	public ResponseEntity<List<PreferenceQuestionResult>> getAllForUser(){
		List<PreferenceQuestionResult> preferenceQuestionRes = new ArrayList<PreferenceQuestionResult>();
		List<Preference> preferences = preferenceService.getAllActive();
		for (Preference preference : preferences) {
			preferenceQuestionRes.add(new PreferenceQuestionResult(preference));
		}
		return new ResponseEntity<>(preferenceQuestionRes, HttpStatus.OK);
	}
	
	
}
