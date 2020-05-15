package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	
	@PostMapping(value="/createQuestion", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> createTraitQuestion(@RequestBody TraitQuestionDto questionDto){
		traitQuestionService.create(questionDto);
		return new ResponseEntity<>(new MessageDto("Success", "Trait question successfully created."), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteQuestion/{questId}")
	public ResponseEntity<MessageDto> deleteTraitQuestion(@PathVariable Long questId){
		TraitQuestion traitQuestion = traitQuestionService.findById(questId);
		if (traitQuestion!=null) {
			traitQuestionService.delete(traitQuestion);
			return new ResponseEntity<>(new MessageDto("Success", "Trait question successfully deleted."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Trait question does not exist."), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value = "/updateQuestion/{questId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> updateTraitQuestion(@PathVariable Long questId, @RequestBody TraitQuestionDto questionDto){
		System.out.println("Uslo u update trait question");
		TraitQuestion traitQuestion = traitQuestionService.findById(questId);
		if (traitQuestion!=null) {
			traitQuestionService.update(questId, questionDto);
			return new ResponseEntity<>(new MessageDto("Success", "Trait question successfully updated."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Trait question does not exist."), HttpStatus.NOT_FOUND);
		}
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
	
	@GetMapping(value = "/{questId}")
	public ResponseEntity<?> getTraitQuestion(@PathVariable Long questId){
		TraitQuestion traitQuestion = traitQuestionService.findById(questId);
		if (traitQuestion!=null) {
			return new ResponseEntity<>(traitQuestion, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found.", "Trait question with given id does not exist"), HttpStatus.NOT_FOUND);
		}
	}
}
