package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.drools.core.ClassObjectFilter;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.PossibleProfession;
import com.sbnz.career.adviser.model.TraitQuestionResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmploymentTest {

	
	@Autowired
	private KieContainer kieContainer;
	
	private KieSession kieSession;
	
	
	
	@Before
    public void before() {
		//kieSession = kieContainer.newKieSession("employmentTest");
		
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		kieSession = kbase.newKieSession();
	}
	
	@Test
	public void possibleProfessionsNotExist() {
		Criteriums criteriums = new Criteriums(false, false, false, true);
		Profession profession1 = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 20000, 15, "", 0d);
		Profession profession3 = new Profession(3l, "Prof C", null, null, null, true, 20000, 15, "", 0d);
		Set<Preference> preferences= new HashSet<Preference>();
		profession1.setActivities(preferences);
		profession2.setActivities(preferences);
		profession3.setActivities(preferences);
		Set<Trait> traits=new HashSet<Trait>();
		profession1.setTraits(traits);
		profession2.setTraits(traits);
		profession3.setTraits(traits);
		kieSession.insert(profession1);
		kieSession.insert(profession2);
		kieSession.insert(profession3);
		kieSession.insert(criteriums);
		
		kieSession.getAgenda().getAgendaGroup("employmentTest").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer) 3, fired);
		
	}
	
	
	@Test
	public void possibleProfessionAlreadyExists() {
		PossibleProfession possibleProfession = new PossibleProfession();
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Preference> preferences= new HashSet<Preference>();
		profession.setActivities(preferences);
		Set<Trait> traits=new HashSet<Trait>();
		profession.setTraits(traits);
		possibleProfession.setProfession(profession);
		kieSession.insert(possibleProfession);
		kieSession.getAgenda().getAgendaGroup("employmentTest").setFocus();
		kieSession.fireAllRules();
		//new RuleNameEqualsAgendaFilter("If there are possible professions inserted, caluculate influence of employment")
	    assertEquals((Double) 0.5, possibleProfession.getScore());
	}
	
	@After
    public void disposeOfSession() {
        this.kieSession.dispose();
    }
	    
}
