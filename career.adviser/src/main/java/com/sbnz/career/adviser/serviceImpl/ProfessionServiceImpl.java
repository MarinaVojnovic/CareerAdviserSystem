package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.repository.PreferenceQuestionResultRepository;
import com.sbnz.career.adviser.repository.ProfessionRepository;
import com.sbnz.career.adviser.repository.TraitsResultRepository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.ProfessionService;


@Service
public class ProfessionServiceImpl implements ProfessionService{
	
	private final KieContainer kieContainer;
	
	private final UserRepository userRepository;
	
	private final ProfessionRepository professionRepository;
	
	private final TraitsResultRepository traitsResultRepository;
	
	private final PreferenceQuestionResultRepository prefQustResultRepository;
	
	public ProfessionServiceImpl(UserRepository userRepository, TraitsResultRepository traitsResultRepository, PreferenceQuestionResultRepository prefQustResultRepository, ProfessionRepository professionRepository, KieContainer kieContainer) {
		this.professionRepository=professionRepository;
		this.kieContainer = kieContainer;
		this.traitsResultRepository=traitsResultRepository;
		this.prefQustResultRepository=prefQustResultRepository;
		this.userRepository=userRepository;
	}
	
	public RecommendedProfessions getResults(Criteriums criteriums) {
		System.out.println("Get results tests just done service called");
		List<Profession> professions = professionRepository.getAllActive();
		TraitsResult traitsResult = traitsResultRepository.getOne(1l);
		User user = userRepository.getOne(1l);
		List<PreferenceQuestionResult> prefQuesRes = prefQustResultRepository.findByUser(user);
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		
		for (Profession prof : professions) {
			kieSession.insert(prof);
		}
		kieSession.insert(traitsResult);
		
		for (PreferenceQuestionResult res : prefQuesRes) {
			kieSession.insert(res);
		}
		RecommendedProfessions recommendedProfessions = new RecommendedProfessions();
		kieSession.insert(recommendedProfessions);
		kieSession.insert(criteriums);
		if (criteriums.getPersonality()==true) {
			System.out.println("personality");
			kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
			kieSession.fireAllRules();
		}
		if (criteriums.getPreferences()==true) {
			System.out.println("preferences");
			kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
			kieSession.fireAllRules();
		}
		if (criteriums.getPayment() == true) {
			System.out.println("payment");
			kieSession.getAgenda().getAgendaGroup("paymentTest").setFocus();
			kieSession.fireAllRules();
			
		}
		if (criteriums.getEmployment()==true) {
			System.out.println("employment");
			kieSession.getAgenda().getAgendaGroup("employmentTest").setFocus();
			kieSession.fireAllRules();
		}
		
		kieSession.getAgenda().getAgendaGroup("results").setFocus();
		kieSession.fireAllRules();
		System.out.println("Recommended professions size: "+recommendedProfessions.getProfessions().size());
		return recommendedProfessions;
	}

}
