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
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.repository.PreferenceQuestionResultRepository;
import com.sbnz.career.adviser.repository.ProfessionRepository;
import com.sbnz.career.adviser.repository.TraitsResultRepository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.ProfessionService;
import com.sbnz.career.adviser.dto.ProfessionDto;

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
	
	public Matching getMatching(Long profId) {
		Profession profession = professionRepository.getOne(profId);
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		kieSession.insert(profession);
		TraitsResult traitsResult = traitsResultRepository.getOne(1l);
		kieSession.insert(traitsResult);
		User user = userRepository.getOne(1l);
		List<PreferenceQuestionResult> prefQuesRes = prefQustResultRepository.findByUser(user);
		for (PreferenceQuestionResult q : prefQuesRes) {
			kieSession.insert(q);
		}
		Matching matching = new Matching();
		kieSession.insert(matching);
		kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("matchingTest").setFocus();
		kieSession.fireAllRules();
		
		return matching;
	}
	
	public ProfessionsSuitabilityList getCandidatesByTraits() {
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		TraitsResult traitsResult = traitsResultRepository.getOne(1l);
		kieSession.insert(traitsResult);
		List<Profession> allProfessions = professionRepository.getAllActive();
		for (Profession profession : allProfessions) {
			kieSession.insert(profession);
		}
		ProfessionsSuitabilityList profSuitList = new ProfessionsSuitabilityList();
		kieSession.insert(profSuitList);
		kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
		kieSession.fireAllRules();
		kieSession.getAgenda().getAgendaGroup("suitableProfessionsTest-traits").setFocus();
		kieSession.fireAllRules();
		return profSuitList;
	}
	
	public ProfessionsSuitabilityList getCandidatesByPreferences() {
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		List<Profession> allProfessions = professionRepository.getAllActive();
		for (Profession profession : allProfessions) {
			kieSession.insert(profession);
		}
		User user = userRepository.getOne(1l);
		List<PreferenceQuestionResult> prefQuestResults = prefQustResultRepository.findByUser(user);
		for (PreferenceQuestionResult q : prefQuestResults) {
			kieSession.insert(q);
		}
		ProfessionsSuitabilityList profSuitList = new ProfessionsSuitabilityList();
		kieSession.insert(profSuitList);
		kieSession.getAgenda().getAgendaGroup("suitableProfessionsTest-preferences").setFocus();
		kieSession.fireAllRules();
		
		
		return profSuitList;
	}
	
	public Profession findById(Long id) {
		return professionRepository.findById(id).orElse(null);
	}
	
	@Override
	public void update(Long profId, ProfessionDto professionDto) {
		Profession profession =  professionRepository.findById(profId).orElse(null);
		profession.setActivities(professionDto.getActivities());
		profession.setDescription(professionDto.getDescription());
		profession.setEmployment(professionDto.getEmployment());
		profession.setPayment(professionDto.getPayment());
		profession.setName(professionDto.getName());
		profession.setTraits(professionDto.getTraits());
		profession.setIsActive(professionDto.getIsActive());
		profession.setField(professionDto.getField());
		professionRepository.save(profession);
	}
	
	@Override
	public void create(ProfessionDto professionDto) {
		Profession profession = new Profession(professionDto);
		professionRepository.save(profession);
	}

}
