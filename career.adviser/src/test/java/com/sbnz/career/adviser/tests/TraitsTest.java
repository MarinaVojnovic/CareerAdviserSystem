package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.model.PossibleProfession;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TraitsTest {

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
	public void ruleZero() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(5l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(t1);
		traits2.add(t2);
		traits2.add(t3);
		profession2.setTraits(traits2);
		profession2.setActivities(preferences);

		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumTraits(3l);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 0 - traits test"));
		
		assertEquals((Double) 1.5, possibleProfession.getScore());
		
	}
	
	@Test
	public void ruleOne() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(5l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(t1);
		traits2.add(t2);
		traits2.add(t3);
		profession2.setTraits(traits2);
		profession2.setActivities(preferences);

		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumTraits(3l);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 1 - traits test"));
		
		assertEquals((Double) 1.0, possibleProfession2.getScore());
		
	}
	
	@Test
	public void ruleTwo() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(4l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(t1);
		traits2.add(t2);
		traits2.add(t3);
		profession2.setTraits(traits2);
		profession2.setActivities(preferences);

		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumTraits(3l);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 2 - traits test"));
		
		assertEquals((Double) 1.25, possibleProfession.getScore());
		
	}
	
	@Test
	public void ruleThree() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(5l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(t1);
		traits2.add(t2);
		traits2.add(t3);
		profession2.setTraits(traits2);
		profession2.setActivities(preferences);

		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumTraits(3l);
		kieSession.insert(possibleProfession2);
		
		
		Profession profession3 = new Profession(2l, "Prof C", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits3 = new HashSet<Trait>();
		traits3.add(t1);
		traits3.add(t2);
		profession3.setTraits(traits3);
		profession3.setActivities(preferences);

		PossibleProfession possibleProfession3 = new PossibleProfession();
		possibleProfession3.setProfession(profession3);
		possibleProfession3.setNumTraits(1l);
		kieSession.insert(possibleProfession3);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 3 - traits test"));
		
		assertEquals((Double)0.5, possibleProfession3.getScore());
		
	}
	
	
	@Test
	public void ruleFour() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(5l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(t1);
		profession2.setTraits(traits2);
		profession2.setActivities(preferences);

		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumTraits(1l);
		kieSession.insert(possibleProfession2);
		
		
		Profession profession3 = new Profession(2l, "Prof C", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits3 = new HashSet<Trait>();
		traits3.add(t1);
		traits3.add(t2);
		traits3.add(t3);
		profession3.setTraits(traits3);
		profession3.setActivities(preferences);

		PossibleProfession possibleProfession3 = new PossibleProfession();
		possibleProfession3.setProfession(profession3);
		possibleProfession3.setNumTraits(2l);
		kieSession.insert(possibleProfession3);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 4 - traits test"));
		
		assertEquals((Double)1.0, possibleProfession3.getScore());
		
	}
	
	
	@Test
	public void ruleFve() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumTraits(1l);
		kieSession.insert(possibleProfession);
		
	
		
		
		Profession profession3 = new Profession(2l, "Prof C", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits3 = new HashSet<Trait>();
		traits3.add(t1);
		traits3.add(t2);
		traits3.add(t3);
		profession3.setTraits(traits3);
		profession3.setActivities(preferences);

		PossibleProfession possibleProfession3 = new PossibleProfession();
		possibleProfession3.setProfession(profession3);
		possibleProfession3.setNumTraits(2l);
		kieSession.insert(possibleProfession3);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 5 - traits test"));
		
		assertEquals((Double) (1.0*1/5), possibleProfession.getScore());
		
	}
	
	@Test
	public void calculatingNumberOfMatchingTraits() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		Trait t1 = new Trait("mind", "extraverted");
		Trait t2 = new Trait("identity", "assertive");
		Trait t3 = new Trait("nature", "thinking");
		Trait t4 = new Trait("energy", "realist");
		Trait t5 = new Trait("tactics", "judging");
		
		traits.add(t1);
		traits.add(t2);
		traits.add(t3);
		traits.add(t4);
		traits.add(t5);
		profession.setTraits(traits);
		
		Set<Preference> preferences = new HashSet<Preference>();
		profession.setActivities(preferences);
		
		kieSession.insert(profession);
		List<Trait> traitList = new ArrayList<Trait>();
		traitList.add(t1);
		traitList.add(t2);
		traitList.add(t3);
		Trait t6=new Trait("tactics", "prospecting");
		Trait t7=new Trait("energy", "visionary");
		traitList.add(t6);
		traitList.add(t7);
		kieSession.insert(traitList);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		Integer fired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Calculating number of traits"));
		
		assertEquals((Integer) 1, fired);
		
	}
	
	
	
}
