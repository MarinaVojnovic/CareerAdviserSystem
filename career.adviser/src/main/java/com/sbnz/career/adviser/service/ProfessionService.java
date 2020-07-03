package com.sbnz.career.adviser.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;

public interface ProfessionService {

	RecommendedProfessions getResults(Criteriums criteriums);
	Matching getMatching(Long profId);
	ProfessionsSuitabilityList getCandidatesByTraits();
	ProfessionsSuitabilityList getCandidatesByPreferences();
	Profession findById(Long id);
	void update(ProfessionDto professionDto);
	void create(ProfessionDto professionDto);
	void delete(Profession profession);
	void activate(Profession profession);
	List<ProfessionDto> getAllActive();
	List<ProfessionDto> getAllDeleted();
	ProfessionDto findOneDto(Long id);
	void uploadImage(MultipartFile imageFile) throws IOException;
	Boolean isTestDone();
	Boolean newTest();
	
}
