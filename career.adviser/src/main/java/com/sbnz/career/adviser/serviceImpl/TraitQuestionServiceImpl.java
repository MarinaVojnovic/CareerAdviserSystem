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

import com.sbnz.career.adviser.dto.TraitQuestionDto;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.repository.TraitQuestionRepository;
import com.sbnz.career.adviser.repository.TraitRepository;
import com.sbnz.career.adviser.service.TraitQuestionService;

@Service
public class TraitQuestionServiceImpl implements TraitQuestionService{


	private final KieContainer kieContainer;
	
	private final TraitQuestionRepository traitQuestionRepository;
	
	private final TraitRepository traitRepository;
	
	@Autowired
	public TraitQuestionServiceImpl(TraitQuestionRepository traitQuestionRepository, KieContainer kieContainer, TraitRepository traitRepository) {
		this.traitQuestionRepository = traitQuestionRepository;
		this.kieContainer = kieContainer;
		this.traitRepository = traitRepository;
	}
	
	
	@Override
	public List<TraitQuestion> getAllActive() {
		// TODO Auto-generated method stub
		return traitQuestionRepository.getAllActive();
	}
	
	@Override
	public TraitQuestion create(TraitQuestion question) {
		
		Trait trait = traitRepository.findByTarget(question.getTrait().getTarget());
		question.setTrait(trait);
		/*
		TraitQuestion traitQuestion = new TraitQuestion(trait, questionDto.getText());
		*/
		return traitQuestionRepository.save(question);
	}

	@Override
	public void delete(TraitQuestion traitQuestion) {
		traitQuestion.setIsActive(false);
		traitQuestionRepository.save(traitQuestion);
		//traitQuestionRepository.delete(traitQuestion);
	}
	
	@Override
	public TraitQuestion findById(Long id) {
		return traitQuestionRepository.findById(id).orElse(null);
	}
	
	@Override
	public void update(Long questId, TraitQuestionDto questDto) {
		TraitQuestion traitQuestion = traitQuestionRepository.getOne(questId);
		traitQuestion.setIsActive(questDto.isActive());
		traitQuestion.setText(questDto.getText());
		Trait trait = traitRepository.findByTarget(questDto.getTarget());
		traitQuestion.setTrait(trait);
		traitQuestionRepository.save(traitQuestion);
		
	}


	@Override
	public List<TraitQuestion> getAll() {
		
		return traitQuestionRepository.getAll();
	}


	@Override
	public void activate(TraitQuestion traitQuestion) {
		traitQuestion.setIsActive(true);
		traitQuestionRepository.save(traitQuestion);
		
	}


	
	

}
