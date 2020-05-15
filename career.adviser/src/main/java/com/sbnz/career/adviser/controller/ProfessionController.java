package com.sbnz.career.adviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.PreferenceQuestionResultDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.service.ProfessionService;


@RestController
@RequestMapping(value = "/profession")
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
	
	
	public ResponseEntity<MessageDto> createProfession(@RequestBody ProfessionDto professionDto){
		return null;
	}
	
	public ResponseEntity<MessageDto> getProfession(@PathVariable Long id){
		return null;
	}
	
	public ResponseEntity<MessageDto> updateProfession(@PathVariable Long id, @RequestBody ProfessionDto professionDto){
		return null;
	}
	
	public ResponseEntity<MessageDto> deleteProfession(@PathVariable Long id){
		return null;
	}
	
	
	
}




