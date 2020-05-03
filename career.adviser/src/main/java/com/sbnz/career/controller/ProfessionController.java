package com.sbnz.career.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.PreferenceQuestionResultDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.model.PreferenceQuestionResult;

public class ProfessionController {

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
	
	public ResponseEntity<List<PreferenceQuestionResult>> getPreferenceQuestions(){
		return null;
	}
	
	public ResponseEntity<MessageDto> submitPreferenceQuestionResults(@RequestBody List<PreferenceQuestionResultDto> preferenceQuestionResultsDto){
		return null;
	}
}




