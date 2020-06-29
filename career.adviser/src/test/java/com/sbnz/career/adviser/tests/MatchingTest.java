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
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.ProfessionalField;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.model.Matching;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchingTest {

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
	public void calculatingNumberOfMatchingTraits() {
		Matching matching = new Matching();
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
		
		List<Trait> traitList = new ArrayList<Trait>();
		traitList.add(t1);
		traitList.add(t2);
		traitList.add(t3);
		Trait t6=new Trait("tactics", "prospecting");
		Trait t7=new Trait("energy", "visionary");
		traitList.add(t6);
		traitList.add(t7);
		
		kieSession.insert(traitList);
		kieSession.insert(matching);
		kieSession.insert(profession);

		kieSession.getAgenda().getAgendaGroup("matchingTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Calculating number of matching traits"));
		assertEquals(3, matching.getTraits().size());
		
		
	}
	
	
	@Test
	public void calculatingNumberOfMatchingPreferences() {
		Matching matching = new Matching();
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> traits = new HashSet<Trait>();
		profession.setTraits(traits);
		
		ProfessionalField field = new ProfessionalField(1l, "Economics");
		Set<Preference> preferences = new HashSet<Preference>();
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2", true,  field);
		Preference pref3 = new Preference(3l, "Preference 3", true,  field);
		Preference pref4 = new Preference(4l, "Preference 4", true, field);
		Preference pref5 = new Preference(5l, "Preference 5",true,  field);
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		preferences.add(pref4);
		preferences.add(pref5);
		profession.setActivities(preferences);

		User user = new User();
		
		PreferenceQuestionResult q1 = new PreferenceQuestionResult(1l, user, pref1, true);
		PreferenceQuestionResult q2 = new PreferenceQuestionResult(2l, user, pref2, true);
		PreferenceQuestionResult q3 = new PreferenceQuestionResult(3l, user, pref3, false);
		kieSession.insert(q1);
		kieSession.insert(q2);
		kieSession.insert(q3);
		kieSession.insert(matching);
		kieSession.insert(profession);

		kieSession.getAgenda().getAgendaGroup("matchingTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Calculating number of matching preferences"));
		assertEquals(2, matching.getPreferences().size());
		
		
	}
}
