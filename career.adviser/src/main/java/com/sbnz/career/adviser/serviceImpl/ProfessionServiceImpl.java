package com.sbnz.career.adviser.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.repository.PreferenceQuestionResultRepository;
import com.sbnz.career.adviser.repository.PreferenceRespository;
import com.sbnz.career.adviser.repository.ProfessionRepository;
import com.sbnz.career.adviser.repository.ProfessionalFieldRepository;
import com.sbnz.career.adviser.repository.TraitRepository;
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
	
	private final TraitRepository traitRepository;
	
	private final ProfessionalFieldRepository profFieldRepository;
	
	private final PreferenceRespository preferenceRepository;
	
	public ProfessionServiceImpl(PreferenceRespository preferenceRepository, ProfessionalFieldRepository profFieldRepository, TraitRepository traitRepository, UserRepository userRepository, TraitsResultRepository traitsResultRepository, PreferenceQuestionResultRepository prefQustResultRepository, ProfessionRepository professionRepository, KieContainer kieContainer) {
		this.professionRepository=professionRepository;
		this.kieContainer = kieContainer;
		this.traitsResultRepository=traitsResultRepository;
		this.prefQustResultRepository=prefQustResultRepository;
		this.userRepository=userRepository;
		this.traitRepository=traitRepository;
		this.profFieldRepository=profFieldRepository;
		this.preferenceRepository=preferenceRepository;
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
		profession.setDescription(professionDto.getDescription());
		profession.setEmployment(professionDto.getEmployment());
		profession.setPayment(professionDto.getPayment());
		profession.setName(professionDto.getName());
		profession.setIsActive(professionDto.getIsActive());
		
		Set<Preference> prefDto = professionDto.getActivities();
		Set<Preference> profPref = new HashSet<Preference>();
		for (Preference pref : prefDto) {
			if(pref.getId()!=null) {
				Preference preference = preferenceRepository.getOne(pref.getId());
				profPref.add(preference);
			}else {
				profPref.add(pref);
				preferenceRepository.save(pref);
			}
			
		}
		profession.setActivities(profPref);
		
		Set<Trait> traits = professionDto.getTraits();
		Set<Trait> profTraits = new HashSet<Trait>();
		for (Trait trait : traits) {
			profTraits.add(this.traitRepository.findByTarget(trait.getTarget()));
		}
		profession.setTraits(profTraits);
		ProfessionalField profField = profFieldRepository.getOne(professionDto.getField().getId());
		profession.setField(profField);
		professionRepository.save(profession);
	}
	
	@Override
	public void create(ProfessionDto professionDto) {
		Set<Trait> traits = professionDto.getTraits();
		Set<Trait> profTraits = new HashSet<Trait>();
		for (Trait trait : traits) {
			profTraits.add(this.traitRepository.findByTarget(trait.getTarget()));
		}
		ProfessionalField profField = profFieldRepository.getOne(professionDto.getField().getId());
		System.out.println("Professional field found: "+profField.getName()+" "+profField.getId());
		Set<Preference> prefDto = professionDto.getActivities();
		Set<Preference> profPref = new HashSet<Preference>();
		for (Preference pref : prefDto) {
			if(pref.getId()!=null) {
				Preference preference = preferenceRepository.getOne(pref.getId());
				profPref.add(preference);
			}else {
				profPref.add(pref);
				preferenceRepository.save(pref);
			}
			
		}
		Profession profession = new Profession(professionDto);
		profession.setTraits(profTraits);
		profession.setField(profField);
		profession.setActivities(profPref);
		professionRepository.save(profession);
	}
	
	@Override
	public void delete(Profession profession) {
		profession.setIsActive(false);
		professionRepository.save(profession);
	}
	
	@Override
	public List<Profession> getAllActive(){
		return professionRepository.getAllActive();
	}

}
