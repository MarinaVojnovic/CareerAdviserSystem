package com.sbnz.career.adviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.TraitsResultService;
import com.sbnz.career.adviser.service.TraitQuestionService;

@RestController
@RequestMapping(value = "/traitsResult")
public class TraitsResultController {

	private TraitsResultService traitsResultService;

	public TraitsResultController(TraitsResultService traitsResultService) {
		this.traitsResultService = traitsResultService;
	}

	@PostMapping(value = "/submitTraitQuestions")
	public ResponseEntity<MessageDto> submitTraitQuestionResults(@RequestBody List<TraitQuestionResult> traitQuestionResults){
		System.out.println("Submit trait question results controller called");
		traitsResultService.submitTraitQuestionResults(traitQuestionResults);
		return new ResponseEntity<>(new MessageDto("Success", "Questions successfully submited."), HttpStatus.OK);
	}
	
}
