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

import com.sbnz.career.adviser.dto.PreferenceDto;
import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.repository.PreferenceRespository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.PreferenceService;

@Service
public class PreferenceServiceImpl implements PreferenceService{

	private final KieContainer kieContainer;
	
	private final PreferenceRespository preferenceRepository;
	
	private final UserRepository userRepository;
	
	@Autowired
	public PreferenceServiceImpl(UserRepository userRepository, PreferenceRespository preferenceRepository, KieContainer kieContainer) {
		this.preferenceRepository = preferenceRepository;
		this.kieContainer = kieContainer;
		this.userRepository=userRepository;
		
	}

	@Override
	public List<Preference> getAllActive() {
		return this.preferenceRepository.getAllActive();
	}
	
	@Override
	public Preference findById(Long id) {
		return this.preferenceRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Preference> getAll(){
		return this.preferenceRepository.getAll();
	}
	
	@Override
	public void create(PreferenceDto prefDto) {
		Preference pref = new Preference(prefDto);
		preferenceRepository.save(pref);
	}
	
	@Override
	public void delete(Preference preference) {
		preference.setIsActive(false);
	}

	@Override
	public void update(Long prefId, PreferenceDto prefDto) {
		Preference preference = preferenceRepository.getOne(prefId);
		preference.setDescription(prefDto.getDescription());
		preference.setIsActive(prefDto.getIsActive());
		preferenceRepository.save(preference);
	}
	

	@Override
	public List<Preference> findByField(ProfessionalField field){
		return preferenceRepository.findByField(field);
	}
	
	
}
