package com.sbnz.career.adviser.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.FileUtils;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbnz.career.adviser.dto.ProfessionDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.PossibleProfession;
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
	
	private final EmploymentScoreTemplateServiceImpl employmentScoreTemplateServiceImpl;
	
	public ProfessionServiceImpl(EmploymentScoreTemplateServiceImpl emplScoreService, PreferenceRespository preferenceRepository, ProfessionalFieldRepository profFieldRepository, TraitRepository traitRepository, UserRepository userRepository, TraitsResultRepository traitsResultRepository, PreferenceQuestionResultRepository prefQustResultRepository, ProfessionRepository professionRepository, KieContainer kieContainer) {
		this.employmentScoreTemplateServiceImpl=emplScoreService;
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
		employmentScoreTemplateServiceImpl.loadTemplates();
		List<Profession> professions = professionRepository.getAllActive();
		for (Profession profession : professions) {
			Set<Preference> preferences=new HashSet<Preference>();
			for (Preference activity : profession.getActivities()) {
				if (activity.getIsActive()==true) {
					preferences.add(activity);
				}
			}
			profession.setActivities(preferences);
		}
		
		User user =  userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		TraitsResult traitsResult = traitsResultRepository.findByUser(user);
		
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
			System.out.println("**PERSONALITY***");
			kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
			kieSession.fireAllRules();
			kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
			kieSession.fireAllRules();
		}
		if (criteriums.getPreferences()==true) {
			System.out.println("***PREFERENCES***");
			kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
			kieSession.fireAllRules();
		}
		if (criteriums.getPayment() == true) {
			System.out.println("***PAYMENT***");
			kieSession.getAgenda().getAgendaGroup("paymentTest").setFocus();
			kieSession.fireAllRules();
			
		}
		if (criteriums.getEmployment()==true) {
			System.out.println("***EMPLOYMENT***");
			kieSession.getAgenda().getAgendaGroup("employmentTest").setFocus();
			kieSession.fireAllRules();
		}
		
		kieSession.getAgenda().getAgendaGroup("results").setFocus();
		kieSession.fireAllRules();
		System.out.println("Recommended professions size: "+recommendedProfessions.getProfessions().size());
		System.out.println("THE RESULTS!!!!!");
		for (PossibleProfession prof : recommendedProfessions.getProfessions()) {
			System.out.println("Profession name: "+prof.getProfession().getName()+" and its score: "+prof.getScore());
		}
	
		return recommendedProfessions;
		
	
		
		
	}
	
	public Matching getMatching(Long profId) {
		
		Profession profession = professionRepository.findById(profId).orElse(null);
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		kieSession.insert(profession);
		
		User user = userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		TraitsResult traitsResult = traitsResultRepository.findByUser(user);
		kieSession.insert(traitsResult);
		
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
		User user =  userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		TraitsResult traitsResult = traitsResultRepository.findByUser(user);
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
		User user =  userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
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
	public void update(ProfessionDto professionDto) {
		Profession profession =  professionRepository.findById(professionDto.getId()).orElse(null);
		try {
			profession.setDescription(new javax.sql.rowset.serial.SerialClob(professionDto.getDescription().toCharArray()));
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		profession.setEmployment(professionDto.getEmployment());
		profession.setPayment(professionDto.getPayment());
		profession.setName(professionDto.getName());
		profession.setIsActive(professionDto.getIsActive());
		
		List<Preference> prefDto = professionDto.getActivities();
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
		
		List<Trait> traits = professionDto.getTraits();
		Set<Trait> profTraits = new HashSet<Trait>();
		for (Trait trait : traits) {
			profTraits.add(this.traitRepository.findByTarget(trait.getTarget()));
		}
		profession.setTraits(profTraits);
		if (professionDto.getImage()!="") {
			profession.setImage(professionDto.getImage());
		}
		professionRepository.save(profession);
	}
	
	@Override
	public void create(ProfessionDto professionDto) {
		List<Trait> traits = professionDto.getTraits();
		Set<Trait> profTraits = new HashSet<Trait>();
		for (Trait trait : traits) {
			profTraits.add(this.traitRepository.findByTarget(trait.getTarget()));
		}
		
		List<Preference> prefDto = professionDto.getActivities();
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
		profession.setActivities(profPref);
		professionRepository.save(profession);
	}
	
	@Override
	public void delete(Profession profession) {
		System.out.println("Uslo u delete u servisu");
		profession.setIsActive(false);
		System.out.println(profession.getIsActive());
		professionRepository.save(profession);
	}
	
	@Override
	public void activate(Profession profession) {
		profession.setIsActive(true);
		professionRepository.save(profession);
	}
	
	@Override
	public List<ProfessionDto> getAllDeleted(){
		List<Profession> professions =  professionRepository.getAllDeleted();
		List<ProfessionDto> professionDtos = new ArrayList<ProfessionDto>();
		for (Profession prof : professions) {
			ProfessionDto profDto = new ProfessionDto(prof);
			Reader r = null;
			try {
				r = prof.getDescription().getCharacterStream();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int j = 0;
			StringBuffer buffer = new StringBuffer();
			int ch;
			try {
				while ((ch = r.read())!=-1) {
				   buffer.append(""+(char)ch);
				}
				profDto.setDescription(buffer.toString());
				professionDtos.add(profDto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return professionDtos;
	}
	
	@Override
	public List<ProfessionDto> getAllActive(){
		List<Profession> professions = professionRepository.getAllActive();
		List<ProfessionDto> professionDtos = new ArrayList<ProfessionDto>();
		for (Profession prof : professions) {
			ProfessionDto profDto = new ProfessionDto(prof);
			Reader r = null;
			try {
				r = prof.getDescription().getCharacterStream();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int j = 0;
			StringBuffer buffer = new StringBuffer();
			int ch;
			try {
				while ((ch = r.read())!=-1) {
				   buffer.append(""+(char)ch);
				}
				profDto.setDescription(buffer.toString());
				professionDtos.add(profDto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return professionDtos;
	}
	
	@Override
	public ProfessionDto findOneDto(Long id) {
		Profession profession = professionRepository.findById(id).orElse(null);
		ProfessionDto profDto = new ProfessionDto(profession);
		
		Reader r = null;
		try {
			r = profession.getDescription().getCharacterStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = 0;
		StringBuffer buffer = new StringBuffer();
		int ch;
		try {
			while ((ch = r.read())!=-1) {
			   buffer.append(""+(char)ch);
			}
			profDto.setDescription(buffer.toString());
		
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profDto;
	}
	

	@Override
	public void uploadImage(MultipartFile imageFile) throws IOException{
		String folder = "src/main/resources/static/";
		byte[] bytes = imageFile.getBytes();
		//Path path = Paths.get(folder + imageFile.getOriginalFilename());
		//Files.newBufferedWriter(path, bytes);
		FileUtils.writeByteArrayToFile(new File(folder+imageFile.getOriginalFilename()), bytes);
	}

	@Override
	public Boolean isTestDone() {
		System.out.println("Uslo u is test done in profession service impl");
		User user =  userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		List<PreferenceQuestionResult> prefQuesRes = prefQustResultRepository.findByUser(user);
		if (prefQuesRes.size()==0) {
			System.out.println("Null je");
			return false;
		}else {
			System.out.println("Nadjeno");
			return true;
		}
	}
	
	
	

}
