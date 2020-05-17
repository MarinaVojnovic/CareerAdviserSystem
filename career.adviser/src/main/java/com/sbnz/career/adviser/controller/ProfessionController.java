package com.sbnz.career.adviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.PreferenceQuestionResultDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.service.ProfessionService;


@RestController
@RequestMapping(value = "/professions")
public class ProfessionController {

	private final ProfessionService professionService;

	public ProfessionController(ProfessionService professionService) {
		this.professionService = professionService;
	}
	
	@GetMapping(value = "/getResults")
	public ResponseEntity<RecommendedProfessions> getResults(Criteriums criteriums){
		System.out.println("Get results tests just done");
		RecommendedProfessions recommendedProfessions = professionService.getResults(criteriums);
		return new ResponseEntity<>(recommendedProfessions, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMatchingTraitsAndPreferences/{profId}")
	public ResponseEntity<Matching> getMatchingTraitsAndPreferences(@PathVariable Long profId){
		System.out.println("Get matching traits and preferences controller called.");
		Matching matching = professionService.getMatching(profId);
		return new ResponseEntity<>(matching, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCandidatesByTraits")
	public ResponseEntity<ProfessionsSuitabilityList> getCandidatesByTraits(){
		System.out.println("Get candidates by traits in controller called.");
		ProfessionsSuitabilityList candidates = professionService.getCandidatesByTraits();
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCandidatesByPreferences")
	public ResponseEntity<ProfessionsSuitabilityList> getCandidatesByPreferences(){
		System.out.println("Get candidates by preferences in controller called.");
		ProfessionsSuitabilityList candidates = professionService.getCandidatesByPreferences();
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
	
	@PostMapping(value="/createProfession", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> createProfession(@RequestBody ProfessionDto professionDto){
		professionService.create(professionDto);
		return new ResponseEntity<>(new MessageDto("Success", "Profession successfully created."), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{profId}")
	public ResponseEntity<?> getProfession(@PathVariable Long profId){
		Profession profession = professionService.findById(profId);
		if (profession!=null) {
			return new ResponseEntity<>(profession, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found.", "Trait question with given id does not exist"), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/updateProfession/{profId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> updateProfession(@PathVariable Long profId, @RequestBody ProfessionDto professionDto){
		System.out.println("Uslo u update profession");
		System.out.println("Id of profession : "+professionDto.getId());
		Profession profession = professionService.findById(profId);
		if (profession!=null) {
			professionService.update(profId, professionDto);
			return new ResponseEntity<>(new MessageDto("Success", "Profession successfully updated."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Profession does not exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/deleteProfession/{profId}")
	public ResponseEntity<MessageDto> deleteProfession(@PathVariable Long profId){
		Profession profession = professionService.findById(profId);
		if (profession!=null) {
			professionService.delete(profession);
			return new ResponseEntity<>(new MessageDto("Success", "Profession successfully deleted."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Profession does not exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}




