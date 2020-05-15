package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.repository.TraitQuestionRepository;

import com.sbnz.career.adviser.repository.TraitsResultRepository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.TraitsResultService;

@Service
public class TraitsResultServiceImpl implements TraitsResultService{

	private final KieContainer kieContainer;
	
	private final TraitsResultRepository traitsResRepo;
	
	private final UserRepository userRepository;
	
	@Autowired
	public TraitsResultServiceImpl(UserRepository userRepository, TraitsResultRepository traitsResRepo, KieContainer kieContainer) {
		this.traitsResRepo = traitsResRepo;
		this.kieContainer = kieContainer;
		this.userRepository = userRepository;
		
	}
	
	@Override
	public void submitTraitQuestionResults(List<TraitQuestionResult> traitQuestionResults) {
		System.out.println("Submit trait question results service called");
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		
		User user = userRepository.getOne(1l);
		for (TraitQuestionResult tr: traitQuestionResults) {
			kieSession.insert(tr);
			
		}
		TraitsResult traitsResult = new TraitsResult();
		kieSession.insert(traitsResult);
		
		kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
		kieSession.fireAllRules();
		
		System.out.println("Traits result");
		System.out.println("Extroverted" +traitsResult.getExtraverted());
		System.out.println("Introverted" + traitsResult.getIntroverted());
		System.out.println("Assertive" + traitsResult.getAssertive());
		System.out.println("Turbulent" + traitsResult.getTurbulent());
		System.out.println("Thinking" + traitsResult.getThinking());
		System.out.println("Feeling" + traitsResult.getFeeling());
		System.out.println("Realist" + traitsResult.getRealist());
		System.out.println("Visionary" + traitsResult.getVisionary());
		System.out.println("Judging"+traitsResult.getJudging());
		System.out.println("Prospecting"+traitsResult.getProspecting());
		
		traitsResult.setUser(user);
		traitsResRepo.save(traitsResult);
		
		
		

		
	}
	
}
