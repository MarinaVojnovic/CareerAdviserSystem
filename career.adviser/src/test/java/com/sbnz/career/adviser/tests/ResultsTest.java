package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
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
import com.sbnz.career.adviser.model.PossibleProfession;
import com.sbnz.career.adviser.model.RecommendedProfessions;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultsTest {

	@Autowired
	private KieContainer kieContainer;
	
	private KieSession kieSession;
	
	
	
	@Before
    public void before() {
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		kieSession = kbase.newKieSession();
	}
	
	@Test
	public void results() {
		Profession profession1 = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 20000, 15, "", 0d);
		Profession profession3 = new Profession(3l, "Prof C", null, null, null, true, 20000, 15, "", 0d);
		Profession profession4 = new Profession(4l, "Prof D", null, null, null, true, 20000, 15, "", 0d);
		Set<Trait> traits= new HashSet<Trait>();
		Set<Preference> preferences = new HashSet<Preference>();
		profession1.setActivities(preferences);
		profession2.setActivities(preferences);
		profession3.setActivities(preferences);
		profession4.setActivities(preferences);
		profession1.setTraits(traits);
		profession2.setTraits(traits);
		profession3.setTraits(traits);
		profession4.setTraits(traits);
		
		
		PossibleProfession poss1 = new PossibleProfession();
		PossibleProfession poss2 = new PossibleProfession();
		PossibleProfession poss3 = new PossibleProfession();
		PossibleProfession poss4 = new PossibleProfession();
		poss1.setProfession(profession1);
		poss2.setProfession(profession2);
		poss3.setProfession(profession3);
		poss4.setProfession(profession4);
		poss1.setScore(1.0);
		poss2.setScore(2.0);
		poss3.setScore(3.0);
		poss4.setScore(4.0);
		
		kieSession.insert(poss1);
		kieSession.insert(poss2);
		kieSession.insert(poss3);
		kieSession.insert(poss4);
		
		RecommendedProfessions rp = new RecommendedProfessions();
		kieSession.insert(rp);
		
		kieSession.getAgenda().getAgendaGroup("results").setFocus();
		kieSession.fireAllRules();
		
		Iterator it = rp.getProfessions().iterator();
		assertEquals(4, rp.getProfessions().size());
	
		
		
	}
}
