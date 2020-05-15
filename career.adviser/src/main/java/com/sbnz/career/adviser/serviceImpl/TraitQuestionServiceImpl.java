package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.repository.TraitQuestionRepository;
import com.sbnz.career.adviser.service.TraitQuestionService;

@Service
public class TraitQuestionServiceImpl implements TraitQuestionService{


	private final KieContainer kieContainer;
	
	private final TraitQuestionRepository traitQuestionRepository;
	
	@Autowired
	public TraitQuestionServiceImpl(TraitQuestionRepository traitQuestionRepository, KieContainer kieContainer) {
		this.traitQuestionRepository = traitQuestionRepository;
		this.kieContainer = kieContainer;
		
	}
	
	
	@Override
	public List<TraitQuestion> getAllActive() {
		// TODO Auto-generated method stub
		return traitQuestionRepository.getAllActive();
	}


	
	

}
