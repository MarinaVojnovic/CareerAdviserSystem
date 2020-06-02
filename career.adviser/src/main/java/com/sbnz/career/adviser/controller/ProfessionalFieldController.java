package com.sbnz.career.adviser.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.service.ProfessionService;
import com.sbnz.career.adviser.service.ProfessionalFieldService;

@RestController
@RequestMapping(value = "/professionalField")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProfessionalFieldController {
	
	private final ProfessionalFieldService professionalFieldService;

	public ProfessionalFieldController(ProfessionalFieldService professionalFieldService) {
		this.professionalFieldService = professionalFieldService;
	}

	@GetMapping
	public ResponseEntity<List<ProfessionalField>> getAllFields() throws SQLException, IOException {
		System.out.println("Get profession fields called.");
		List<ProfessionalField> fields = professionalFieldService.getAllFields();
		return new ResponseEntity<>(fields, HttpStatus.OK);
	}
	
	@PostMapping(value="/addField", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDto> createProfession(@RequestBody ProfessionalField professionalField){
		System.out.println("Field added");
		professionalFieldService.create(professionalField);
		return new ResponseEntity<>(new MessageDto("Success", "Professional field successfully created."), HttpStatus.CREATED);
	}
	
}
