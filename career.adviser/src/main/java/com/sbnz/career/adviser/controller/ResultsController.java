package com.sbnz.career.adviser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.service.ProfessionService;
import com.sbnz.career.adviser.service.ResultsService;

@RestController
@RequestMapping(value = "/results")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ResultsController {

	private ResultsService resultsService;

	public ResultsController(ResultsService resultsService) {
		this.resultsService = resultsService;
	}
	
	
	
}
