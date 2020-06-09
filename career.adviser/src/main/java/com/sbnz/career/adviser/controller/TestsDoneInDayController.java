package com.sbnz.career.adviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.entity.TestsDoneInDay;
import com.sbnz.career.adviser.service.TestsDoneInDayService;

@RestController
@RequestMapping(value = "/report")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TestsDoneInDayController {
	
	private TestsDoneInDayService testsDoneInDayService;
	
	public TestsDoneInDayController(TestsDoneInDayService testsDoneInDayService) {
		this.testsDoneInDayService=testsDoneInDayService;
	}
	
	@GetMapping(value = "/getReport")
	public ResponseEntity<List<TestsDoneInDay>> getReport(){
		System.out.println("Uslo u get report controller");
		List<TestsDoneInDay> report = testsDoneInDayService.getReport();
		return new ResponseEntity<>(report, HttpStatus.OK);
		
	}

}
