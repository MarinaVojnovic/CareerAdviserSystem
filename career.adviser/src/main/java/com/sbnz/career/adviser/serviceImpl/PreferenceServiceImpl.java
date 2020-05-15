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

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
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

	
	
	
}
