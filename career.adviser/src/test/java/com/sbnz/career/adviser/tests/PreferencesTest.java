package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

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
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.PossibleProfession;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PreferencesTest {

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
	public void calculatingNumberOfMatchingActivities() {
		Criteriums criteriums = new Criteriums(true, false, false, false);
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		
		User user = new User();
		
		PreferenceQuestionResult q1 = new PreferenceQuestionResult(1l, user, pref1, true);
		PreferenceQuestionResult q2 = new PreferenceQuestionResult(2l, user, pref2, true);
		PreferenceQuestionResult q3 = new PreferenceQuestionResult(3l, user, pref3, false);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		kieSession.insert(q1);
		kieSession.insert(q2);
		kieSession.insert(q3);
		kieSession.insert(criteriums);
		kieSession.insert(possibleProfession);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Calculating number of matching activities"));
		
		
		assertEquals((Long)2l, possibleProfession.getNumPref());
		
		
	}
	
	
	@Test
	public void noPossibleProfessions() {
		Criteriums criteriums = new Criteriums(false, true, false, false);
		System.out.println("Personality: "+criteriums.getPersonality());
		System.out.println("Preferences: "+criteriums.getPreferences());
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		
		User user = new User();
		
		PreferenceQuestionResult q1 = new PreferenceQuestionResult(1l, user, pref1, true);
		PreferenceQuestionResult q2 = new PreferenceQuestionResult(2l, user, pref2, true);
		PreferenceQuestionResult q3 = new PreferenceQuestionResult(3l, user, pref3, true);
		
		kieSession.insert(q1);
		kieSession.insert(q2);
		kieSession.insert(q3);
		kieSession.insert(criteriums);
		kieSession.insert(profession);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		Integer fired =kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("If there are not possible profession, find and insert them"));
		
		
		assertEquals((Integer)1, fired);
		
		
	}
	
	@Test
	public void ruleZero() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(3l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5", true, field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(2l);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 0 - preferences test"));
		
		assertEquals((Double) 1.5, possibleProfession.getScore());
		
	}
	
	@Test
	public void ruleOne() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(3l);
		kieSession.insert(possibleProfession);
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5", true, field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(2l);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 1 - preferences test"));
		
		assertEquals((Double) 1.0, possibleProfession2.getScore());
		
	}
	
	
	@Test
	public void ruleTwo() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		

		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(3l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5", true, field2);
		Preference pref6 = new Preference(6l, "Preference 6", true, field2);
		Preference pref7 = new Preference(7l, "Preference 7", true, field2);
		Preference pref8 = new Preference(8l, "Preference 8", true, field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		preferences2.add(pref6);
		preferences2.add(pref7);
		preferences2.add(pref8);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(4l);
		
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 2 - preferences test"));
		
		assertEquals((Double) 1.25, possibleProfession2.getScore());
		
	}
	
	
	@Test
	public void ruleThree() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		profession.setActivities(preferences);
		

		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(3l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5", true, field2);
		Preference pref6 = new Preference(6l, "Preference 6", true, field2);
		Preference pref7 = new Preference(7l, "Preference 7", true, field2);
		Preference pref8 = new Preference(8l, "Preference 8", true, field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		preferences2.add(pref6);
		preferences2.add(pref7);
		preferences2.add(pref8);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(5l);
		
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 3 - preferences test"));
		
		
		Profession profession3 = new Profession(3l, "Prof C", null, null, null, true, 10000, 5, "", 0.5);
		profession3.setTraits(traits);
		Preference pref9 = new Preference(9l, "Preference 9", true, field);
		Preference pref10 = new Preference(10l, "Preference 10",true, field);
		Set<Preference> preferences3 = new HashSet<Preference>();
		preferences.add(pref9);
		preferences.add(pref10);
		profession.setActivities(preferences3);
		

		PossibleProfession possibleProfession3 = new PossibleProfession();
		possibleProfession3.setProfession(profession3);
		possibleProfession3.setNumPref(1l);
		kieSession.insert(possibleProfession2);
		
		assertEquals((Double) (1/2*1.0), possibleProfession2.getScore());
		
	}
	
	@Test
	public void ruleFour() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		profession.setActivities(preferences);
		

		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(2l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5",true, field2);
		Preference pref6 = new Preference(6l, "Preference 6", true, field2);
		Preference pref7 = new Preference(7l, "Preference 7", true, field2);
		Preference pref8 = new Preference(8l, "Preference 8", true,field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		preferences2.add(pref6);
		preferences2.add(pref7);
		preferences2.add(pref8);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(5l);
		
		kieSession.insert(possibleProfession2);
		
		Profession profession3 = new Profession(3l, "Prof C", null, null, null, true, 10000, 5, "", 0.5);
		profession3.setTraits(traits);
		Preference pref9 = new Preference(9l, "Preference 9", true, field);
		Preference pref10 = new Preference(10l, "Preference 10", true, field);
		Preference pref11 = new Preference(11l, "Preference 11", true, field);
		Preference pref12 = new Preference(12l, "Preference 12",true,  field);
		Set<Preference> preferences3 = new HashSet<Preference>();
		preferences3.add(pref9);
		preferences3.add(pref10);
		preferences3.add(pref11);
		preferences3.add(pref12);
		profession3.setActivities(preferences3);
		

		PossibleProfession possibleProfession3 = new PossibleProfession();
		possibleProfession3.setProfession(profession3);
		possibleProfession3.setNumPref(3l);
		kieSession.insert(possibleProfession3);
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 4 - preferences test"));
		
		
		assertEquals((Double) 1.0, possibleProfession3.getScore());
		
	}
	
	@Test
	public void ruleFive() {
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true, field);
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		profession.setActivities(preferences);
		

		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		possibleProfession.setNumPref(1l);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		profession2.setTraits(traits);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5",true, field2);
		Preference pref6 = new Preference(6l, "Preference 6", true, field2);
		Preference pref7 = new Preference(7l, "Preference 7", true, field2);
		Preference pref8 = new Preference(8l, "Preference 8", true,field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		preferences2.add(pref6);
		preferences2.add(pref7);
		preferences2.add(pref8);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		possibleProfession2.setNumPref(3l);
		
		kieSession.insert(possibleProfession2);
		
	
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 5 - preferences test"));
		
		
		assertEquals((Double) 0.5, possibleProfession.getScore());
		
	}
	
	@After
    public void disposeOfSession() {
		this.kieSession.dispose();
    }
	
}
