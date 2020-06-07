package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.TraitQuestionService;
import com.sbnz.career.adviser.service.TraitService;

@RestController
@RequestMapping(value = "/personality")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TraitsController {
	
	private TraitQuestionService traitQuestionService;
	
	private TraitService traitService;
	
	

	public TraitsController(TraitQuestionService traitQuestionService, TraitService traitService) {
		this.traitQuestionService = traitQuestionService;
		this.traitService = traitService;
		
	}
	
	@GetMapping(value = "/newTest")
	public ResponseEntity<MessageDto> newTest(){
		System.out.println("New test called.");
		
		Boolean permision  = traitService.newTest();
		if (permision == true) {
			return new ResponseEntity<>(new MessageDto("Success", "You can now retake the test."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Forbidden", "Access forbidden for three minutes"), HttpStatus.FORBIDDEN);
		}
		
		
	}
	
	@GetMapping(value = "/getTraits")
	public ResponseEntity<List<Trait>> getTraits(){
		System.out.println("Get traits called.");
		List<Trait> traits = traitService.getAll();
		return new ResponseEntity<>(traits, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByPersonalityField/{persField}")
	public ResponseEntity<List<Trait>> getByPersonalityField(@PathVariable String persField){
		System.out.println("Uslo u get by personality field.");
		List<Trait> traits = traitService.getByPersonalityField(persField);
		System.out.println("SIZE: "+traits.size());
		return new ResponseEntity<>(traits, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getTraitQuestions")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<TraitQuestion>> getTraitQuestions(){
		System.out.println("Get trait questions called.");
		List<TraitQuestion> traitQuestions = traitQuestionService.getAll();
		return new ResponseEntity<>(traitQuestions, HttpStatus.OK);
	}

	
	@PostMapping(value="/createQuestion", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> createTraitQuestion(@RequestBody TraitQuestion question){
		traitQuestionService.create(question);
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
	
	@DeleteMapping(value = "/activateQuestion/{questId}")
	public ResponseEntity<MessageDto> activateTraitQuestion(@PathVariable Long questId){
		TraitQuestion traitQuestion = traitQuestionService.findById(questId);
		if (traitQuestion!=null) {
			traitQuestionService.activate(traitQuestion);
			return new ResponseEntity<>(new MessageDto("Success", "Trait question successfully activated."), HttpStatus.OK);
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
		System.out.println("Uslo u get questions for user");
		List<TraitQuestionResult> traitQuestionResult = new ArrayList<TraitQuestionResult>();
		List<TraitQuestion> traitQuestions = traitQuestionService.getAllActive();
		for (TraitQuestion traitQuest : traitQuestions) {
			traitQuestionResult.add(new TraitQuestionResult(traitQuest));
		}
		Collections.shuffle(traitQuestionResult);
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
	
	@PostMapping(value="/create")
	public ResponseEntity<MessageDto> createProfession(@RequestBody Trait trait){
		traitService.create(trait);
		return new ResponseEntity<>(new MessageDto("Success", "Trait successfully created."), HttpStatus.CREATED);
	}
}
