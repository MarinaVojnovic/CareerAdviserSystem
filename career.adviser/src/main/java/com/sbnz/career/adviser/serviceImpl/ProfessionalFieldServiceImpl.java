package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.repository.PreferenceRespository;
import com.sbnz.career.adviser.repository.ProfessionalFieldRepository;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.service.ProfessionalFieldService;

@Service
public class ProfessionalFieldServiceImpl implements ProfessionalFieldService{

	private final ProfessionalFieldRepository professionalFieldRepository;
	
	@Autowired
	public ProfessionalFieldServiceImpl(ProfessionalFieldRepository professionalFieldRepository) {
		this.professionalFieldRepository = professionalFieldRepository;
		
		
	}
	 @Override
	 public List<ProfessionalField> getAllFields(){
		 return this.professionalFieldRepository.getAll();
	 }
	 
	 @Override
	 public void create(ProfessionalField professionalField) {
		 this.professionalFieldRepository.save(professionalField);
	 }
}
