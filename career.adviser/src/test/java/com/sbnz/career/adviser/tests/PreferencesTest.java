package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
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
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.ProfessionalField;
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
	public void ruleZero() {
		Criteriums criteriums = new Criteriums(true, false, false, false);
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
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
		
		PossibleProfession possibleProfession = new PossibleProfession();
		possibleProfession.setProfession(profession);
		kieSession.insert(q1);
		kieSession.insert(q2);
		kieSession.insert(q3);
		kieSession.insert(criteriums);
		kieSession.insert(possibleProfession);
		
		
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 5, "", 0.5);
		ProfessionalField field2 = new ProfessionalField(1l, "Engineering");
		Preference pref4 = new Preference(4l, "Preference 4", true, field2);
		Preference pref5 = new Preference(5l, "Preference 5", true, field2);
		Set<Preference> preferences2 = new HashSet<Preference>();
		preferences2.add(pref4);
		preferences2.add(pref5);
		profession2.setActivities(preferences2);
		PossibleProfession possibleProfession2 = new PossibleProfession();
		possibleProfession2.setProfession(profession2);
		PreferenceQuestionResult q4 = new PreferenceQuestionResult(1l, user, pref4, true);
		PreferenceQuestionResult q5 = new PreferenceQuestionResult(2l, user, pref5, true);
		kieSession.insert(q4);
		kieSession.insert(q5);
		kieSession.insert(criteriums);
		kieSession.insert(possibleProfession2);
		
		
		
		
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Rule 0 - preferences test"));
		
		assertEquals((Double) 1.5, possibleProfession.getScore());
		
	}
}
