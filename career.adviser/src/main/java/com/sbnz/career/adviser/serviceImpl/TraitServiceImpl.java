package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.repository.TraitQuestionRepository;
import com.sbnz.career.adviser.repository.TraitRepository;
import com.sbnz.career.adviser.service.TraitService;

@Service
public class TraitServiceImpl implements TraitService{

	private final TraitRepository traitRepository;
	
	@Autowired
	public TraitServiceImpl(TraitRepository traitRepository) {
		this.traitRepository = traitRepository;
		
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
}
