package com.sbnz.career.controller;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.model.TraitsResult;

public class PersonalityController {

	public ResponseEntity<TraitsResult> submitTraitQuestionResults(@RequestBody List<TraitQuestionResult> questionResult){
		return null;
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
	
	public ResponseEntity<List<TraitQuestionDto>> getAllForAdmin(){
		return null;
	}
	
	public ResponseEntity<List<TraitQuestionResult>> getAllForUser(){
		return null;
	}
}
