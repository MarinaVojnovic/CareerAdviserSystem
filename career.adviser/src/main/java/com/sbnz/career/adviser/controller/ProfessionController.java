package com.sbnz.career.adviser.controller;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.PreferenceQuestionResultDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.EmploymentScoreTemplate;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.service.EmploymentScoreTemplateService;
import com.sbnz.career.adviser.service.ProfessionService;


@RestController
@RequestMapping(value = "/professions")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProfessionController {

	private final ProfessionService professionService;
	
	private final EmploymentScoreTemplateService employmentScoreTemplateService;

	public ProfessionController(ProfessionService professionService, EmploymentScoreTemplateService employmentScoreTemplateService) {
		this.professionService = professionService;
		this.employmentScoreTemplateService=employmentScoreTemplateService;
	}
	
	@PostMapping(value = "/newEmploymentScoreTemplates",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> newEmploymentScoreTemplates(@RequestBody List<EmploymentScoreTemplate> templates){
		System.out.println("New employment score templates called");
		this.employmentScoreTemplateService.newTemplates(templates);
		return new ResponseEntity<>(new MessageDto("Success", "Templates successfully created."), HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/uploadImage")
	public ResponseEntity<MessageDto> uploadImage(@RequestParam("file") MultipartFile imageFile){
		System.out.println("Uploag image called");
		System.out.println(imageFile);
		try {
			professionService.uploadImage(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value = "/getResults",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecommendedProfessions> getResults(@RequestBody Criteriums criteriums){
		System.out.println("Get results tests just done");
		
		RecommendedProfessions recommendedProfessions = professionService.getResults(criteriums);
		return new ResponseEntity<>(recommendedProfessions, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/isTestDone")
	public ResponseEntity<Boolean> isTestDone(){
		System.out.println("Uslo u get traitsResult");
		Boolean result = professionService.isTestDone();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
		
		
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
	
	@PostMapping(value="/createProfession")
	public ResponseEntity<MessageDto> createProfession(@RequestBody ProfessionDto professionDto){
		professionService.create(professionDto);
		return new ResponseEntity<>(new MessageDto("Success", "Profession successfully created."), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{profId}")
	public ResponseEntity<ProfessionDto> getProfession(@PathVariable Long profId){
		System.out.println("GET ONE PROFESSION CALLED");
		ProfessionDto profession = professionService.findOneDto(profId);
		
		return new ResponseEntity<>(profession, HttpStatus.OK);
		
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
	
	@GetMapping
	public ResponseEntity<List<ProfessionDto>> getActiveProfessions() throws SQLException, IOException {
		System.out.println("Get active professions called.");
		List<ProfessionDto> professions = professionService.getAllActive();
		
		
		return new ResponseEntity<>(professions, HttpStatus.OK);
	}
	
	
}




