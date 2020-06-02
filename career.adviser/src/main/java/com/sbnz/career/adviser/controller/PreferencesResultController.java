package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.PreferenceDto;
import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.PreferenceQuestionResultService;
import com.sbnz.career.adviser.service.PreferenceService;

@RestController
@RequestMapping(value = "/preferencesResult")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PreferencesResultController {

	
	private PreferenceQuestionResultService prefQuesResSer;
	

	public PreferencesResultController(PreferenceQuestionResultService prefQuesResSer) {
		this.prefQuesResSer = prefQuesResSer;
	}
	
	@PostMapping(value = "/submitPreferenceQuestions")
	public ResponseEntity<MessageDto> submitTraitQuestionResults(@RequestBody List<PreferenceQuestionResult> traitQuestionResults){
		System.out.println("Submit preference question results controller called");
		prefQuesResSer.submitPreferenceQuestionResults(traitQuestionResults);
		return new ResponseEntity<>(new MessageDto("Success", "Questions successfully submitted."), HttpStatus.OK);
	}
	
	
}
