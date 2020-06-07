package com.sbnz.career.adviser.serviceImpl;

import java.io.InputStream;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.EmploymentScoreTemplate;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.repository.EmploymentScoreTemplateRepository;
import com.sbnz.career.adviser.repository.ProfessionRepository;
import com.sbnz.career.adviser.service.EmploymentScoreTemplateService;
import org.kie.api.builder.Message;

@Service
public class EmploymentScoreTemplateServiceImpl implements EmploymentScoreTemplateService{

	private EmploymentScoreTemplateRepository employmentScoreRepository;
	private ProfessionRepository professionRepository;
	
	public EmploymentScoreTemplateServiceImpl(ProfessionRepository professionRepository, EmploymentScoreTemplateRepository employmentScoreTemplateRepository) {
		this.employmentScoreRepository=employmentScoreTemplateRepository;
		this.professionRepository=professionRepository;
	}
	
	@Override
	public void loadTemplates() {
		List<EmploymentScoreTemplate> templates = employmentScoreRepository.findAll();
		InputStream template = EmploymentScoreTemplateServiceImpl.class.getResourceAsStream("/templates/employmentScore.drt");
		ObjectDataCompiler converter = new ObjectDataCompiler();
	    String drl = converter.compile(templates, template);
	    KieSession ksession = initializeKieSessionFromDRL(drl);
	    List<Profession> professions = professionRepository.getAllActive();
	    for(Profession prof : professions){
			
			ksession.insert(prof);
			
		}
	    ksession.fireAllRules();
	    for (Profession prof :professions) {
	    	System.out.println("Employment score: "+prof.getEmploymentScore());
	    	professionRepository.save(prof);
	    }
        ksession.dispose();
      
	}
	
	@Override
	public void newTemplates(List<EmploymentScoreTemplate> templates) {
		employmentScoreRepository.deleteAll();
		InputStream template = EmploymentScoreTemplateServiceImpl.class.getResourceAsStream("/templates/employmentScore.drt");
		ObjectDataCompiler converter = new ObjectDataCompiler();
	    String drl = converter.compile(templates, template);
	    KieSession ksession = initializeKieSessionFromDRL(drl);
	    List<Profession> professions = professionRepository.getAllActive();
	    for(Profession prof : professions){
			
			ksession.insert(prof);
			
		}
	    ksession.fireAllRules();
	    for (Profession prof :professions) {
	    	System.out.println("Employment score: "+prof.getEmploymentScore());
	    	professionRepository.save(prof);
	    }
        ksession.dispose();
        for(EmploymentScoreTemplate temp : templates) {
        	employmentScoreRepository.save(temp);
        }
      
	}
	
	private KieSession initializeKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}
