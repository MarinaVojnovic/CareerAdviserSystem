package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.TraitQuestionService;

@RestController
@RequestMapping(value = "/traits")
public class TraitsController {
	
	private TraitQuestionService traitQuestionService;

	public TraitsController(TraitQuestionService traitQuestionService) {
		this.traitQuestionService = traitQuestionService;
	}

	
	
	public ResponseEntity<MessageDto> createTraitQuestion(@RequestBody TraitQuestionDto questionDto){
		return null;
	}
	
	public ResponseEntity<MessageDto> deleteTraitQuestion(@PathVariable(value = "") Long questionId){
		return null;
	}
	
	public ResponseEntity<MessageDto> updateTraitQuestion(@PathVariable Long id, @RequestBody TraitQuestionDto questionDto){
		return null;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TraitQuestionDto>> getAllForAdmin(){
		List<TraitQuestionDto> list = new ArrayList<TraitQuestionDto>();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllQuestionsForUser")
	public ResponseEntity<List<TraitQuestionResult>> getAllForUser(){
		List<TraitQuestionResult> traitQuestionResult = new ArrayList<TraitQuestionResult>();
		List<TraitQuestion> traitQuestions = traitQuestionService.getAllActive();
		for (TraitQuestion traitQuest : traitQuestions) {
			traitQuestionResult.add(new TraitQuestionResult(traitQuest));
		}
		return new ResponseEntity<>(traitQuestionResult, HttpStatus.OK);
	}
}
