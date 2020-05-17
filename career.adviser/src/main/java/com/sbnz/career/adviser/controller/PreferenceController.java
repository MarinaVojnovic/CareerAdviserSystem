package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
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
import com.sbnz.career.adviser.dto.PreferenceDto;
import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.TraitQuestion;
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
	
	@GetMapping(value = "/{prefId}")
	public ResponseEntity<?> getPreference(@PathVariable Long prefId){
		Preference preference = preferenceService.findById(prefId);
		if (preference!=null) {
			return new ResponseEntity<>(preference, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found.", "Preference with given id does not exist"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Preference>> getAll(){
		List<Preference> preferences = preferenceService.getAll();
		return new ResponseEntity<>(preferences, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllActive")
	public ResponseEntity<List<Preference>> getAllActive(){
		List<Preference> preferences = preferenceService.getAllActive();
		return new ResponseEntity<>(preferences, HttpStatus.OK);
	}
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> createPreference(@RequestBody PreferenceDto preferenceDto){
		preferenceService.create(preferenceDto);
		return new ResponseEntity<>(new MessageDto("Success", "Preference successfully created."), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{prefId}")
	public ResponseEntity<MessageDto> delete(@PathVariable Long prefId){
		Preference preference = preferenceService.findById(prefId);
		if (preference!=null) {
			preferenceService.delete(preference);
			return new ResponseEntity<>(new MessageDto("Success", "Preference successfully deleted."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Preference does not exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/update/{prefId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> updateTraitQuestion(@PathVariable Long prefId, @RequestBody PreferenceDto preferenceDto){
		System.out.println("Uslo u update preference");
		Preference preference = preferenceService.findById(prefId);
		if (preference!=null) {
			preferenceService.update(prefId, preferenceDto);
			return new ResponseEntity<>(new MessageDto("Success", "Preference successfully updated."), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDto("Not found", "Preference does not exist."), HttpStatus.NOT_FOUND);
		}
	}
}
