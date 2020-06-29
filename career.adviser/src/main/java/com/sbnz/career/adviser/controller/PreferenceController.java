package com.sbnz.career.adviser.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.service.PreferenceService;
import com.sbnz.career.adviser.service.TraitQuestionService;

@RestController
@RequestMapping(value = "/preferences")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
		Collections.shuffle(preferenceQuestionRes);
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
	
	@PostMapping(value = "/findByField")
	public ResponseEntity<List<Preference>> getPreference(@RequestBody ProfessionalField profField){
		System.out.println("Uslo u find by field - preferences");
		List<Preference> preference = preferenceService.findByField(profField);
		return new ResponseEntity<>(preference, HttpStatus.OK);
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
		System.out.println("Uslo u create preference");
		System.out.println("Prference dto field: "+preferenceDto.getField().getName());
		preferenceService.create(preferenceDto);
		return new ResponseEntity<>(new MessageDto("Success", "Preference successfully created."), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{prefId}")
	public ResponseEntity<MessageDto> delete(@PathVariable Long prefId){
		System.out.println("Uslo u delete activity");
		Preference preference = preferenceService.findById(prefId);
		if (preference!=null) {
			System.out.println("Nije null");
			preferenceService.delete(preference);
			return new ResponseEntity<>(new MessageDto("Success", "Preference successfully deleted."), HttpStatus.OK);
		}else {
			System.out.println("Null je");
			return new ResponseEntity<>(new MessageDto("Not found", "Preference does not exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	  @GetMapping(value = "/activate/{prefId}") public ResponseEntity<MessageDto>
	  activate(@PathVariable Long prefId){
	  System.out.println("Uslo u acitvate activity"); Preference preference =
	  preferenceService.findById(prefId); if (preference!=null) {
	  System.out.println("Nije null"); preferenceService.activate(preference);
	  return new ResponseEntity<>(new MessageDto("Success",
	  "Preference successfully activated."), HttpStatus.OK); }else {
	  System.out.println("Null je"); return new ResponseEntity<>(new
	  MessageDto("Not found", "Preference does not exist."), HttpStatus.NOT_FOUND);
	  } }
	 
	
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
