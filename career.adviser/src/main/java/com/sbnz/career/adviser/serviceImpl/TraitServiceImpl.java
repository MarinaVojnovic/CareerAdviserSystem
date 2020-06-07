package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.events.LoginEvent;
import com.sbnz.career.adviser.events.NewPersonalityTestEvent;
import com.sbnz.career.adviser.repository.TraitQuestionRepository;
import com.sbnz.career.adviser.repository.TraitRepository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.TraitService;

@Service
public class TraitServiceImpl implements TraitService{

	private final TraitRepository traitRepository;
	private KieSession kieSession;
	private UserRepository userRepository;
	
	
	public TraitServiceImpl(UserRepository userRepository, @Qualifier("newPersTestSession") KieSession kieSession, TraitRepository traitRepository) {
		this.traitRepository = traitRepository;
		this.kieSession=kieSession;
		this.userRepository=userRepository;
		
	}
	@Override
	public List<Trait> getAll(){
		return this.traitRepository.getAll();
	}
	@Override
	public void create(Trait trait) {
		this.traitRepository.save(trait);
		
	}
	@Override
	public List<Trait> getByPersonalityField(String persField) {
		
		return traitRepository.getByPersonalityField(persField);
	}
	
	@Override
	public Boolean newTest() {
		User user = userRepository.getOne(2l);
		NewPersonalityTestEvent newTest = new NewPersonalityTestEvent(user);
		kieSession.insert(newTest);
		kieSession.getAgenda().getAgendaGroup("newPersonalityTest").setFocus();
		kieSession.fireAllRules();
		userRepository.save(user);
		return user.getNewPersTest();
		
	}
}
