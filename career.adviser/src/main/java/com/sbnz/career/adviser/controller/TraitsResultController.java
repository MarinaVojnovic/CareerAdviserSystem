package com.sbnz.career.adviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.TraitsResultService;
import com.sbnz.career.adviser.service.TraitQuestionService;

@RestController
@RequestMapping(value = "/traitsResult")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TraitsResultController {

	private TraitsResultService traitsResultService;

	public TraitsResultController(TraitsResultService traitsResultService) {
		this.traitsResultService = traitsResultService;
	}
	
	@GetMapping(value = "/isTestDone")
	public ResponseEntity<?> isTestDone(){
		System.out.println("Uslo u get traitsResult");
		TraitsResult traitsResult = traitsResultService.getTraitResult();
		if (traitsResult == null) {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		
	}

	@PostMapping(value = "/submitTraitQuestions")
	public ResponseEntity<MessageDto> submitTraitQuestionResults(@RequestBody List<TraitQuestionResult> traitQuestionResults){
		System.out.println("Submit trait question results controller called");
		traitsResultService.submitTraitQuestionResults(traitQuestionResults);
		return new ResponseEntity<>(new MessageDto("Success", "Questions successfully submited."), HttpStatus.OK);
	}
	

	@GetMapping
	public ResponseEntity<TraitsResult> getTraitsResult(){
		System.out.println("Uslo u get traitsResult");
		TraitsResult traitsResult = traitsResultService.getTraitResult();
		System.out.println(traitsResult.getExtraverted());
		System.out.println(traitsResult.getIntroverted());
		System.out.println(traitsResult.getAssertive());
		return new ResponseEntity<>(traitsResult, HttpStatus.OK);
		
	}
	

	
	
	
}
