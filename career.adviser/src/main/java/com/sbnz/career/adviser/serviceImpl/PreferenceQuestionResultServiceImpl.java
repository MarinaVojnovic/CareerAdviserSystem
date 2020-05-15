package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.repository.PreferenceQuestionResultRepository;
import com.sbnz.career.adviser.repository.PreferenceRespository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.PreferenceQuestionResultService;

@Service
public class PreferenceQuestionResultServiceImpl implements PreferenceQuestionResultService{

	private final KieContainer kieContainer;
	
	private final PreferenceQuestionResultRepository prefQuesResRep;
	
	private final UserRepository userRepository;
	
	@Autowired
	public PreferenceQuestionResultServiceImpl(UserRepository userRepository, PreferenceQuestionResultRepository prefQuesResRep, KieContainer kieContainer) {
		this.prefQuesResRep = prefQuesResRep;
		this.kieContainer = kieContainer;
		this.userRepository=userRepository;
		
	}
	
	@Override
	public void submitPreferenceQuestionResults(List<PreferenceQuestionResult> prefQuestResults) {
		System.out.println("Submit preference question results service called");
		User user = userRepository.getOne(1l);
		for (PreferenceQuestionResult prefQuestRes: prefQuestResults) {
			prefQuestRes.setUser(user);
			prefQuesResRep.save(prefQuestRes);
		}
		
	}
}
