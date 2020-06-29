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
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuitableProfessionsTest {

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
	public void candidateProfessionsBasedOnTraits() {
		List<Trait> traits = new ArrayList<Trait>();
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
		
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Set<Trait> profTraits = new HashSet<Trait>();
		profTraits.add(t1);
		profTraits.add(t2);
		Trait t6 = new Trait("nature", "feeling");
		Trait t7 = new Trait("energy", "visionary");
		Trait t8 = new Trait("tactics", "prospecting");
		profTraits.add(t6);
		profTraits.add(t7);
		profTraits.add(t8);
		profession.setTraits(profTraits);
		
		ProfessionsSuitabilityList profSuitList = new ProfessionsSuitabilityList();
		
		kieSession.insert(traits);
		kieSession.insert(profession);
		kieSession.insert(profSuitList);
		kieSession.getAgenda().getAgendaGroup("suitableProfessionsTest-traits").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Find candidate professions based on traits"));
		assertEquals( 2, profSuitList.getSuitableProfessions().get(0).getMatching().getTraits().size());
		
	}
	
	@Test
	public void candidateProfessionsBasedOnPreferences() {
		ProfessionsSuitabilityList profSuitList = new ProfessionsSuitabilityList();
		kieSession.insert(profSuitList);
		
		ProfessionalField field = new ProfessionalField(1l, "Economy");
		Preference pref1 = new Preference(1l, "Preference 1", true, field);
		Preference pref2 = new Preference(2l, "Preference 2",true, field);
		Preference pref3 = new Preference(3l, "Preference 3", true, field);
		User user = new User();
		PreferenceQuestionResult q1 = new PreferenceQuestionResult(1l, user, pref1, true);
		PreferenceQuestionResult q2 = new PreferenceQuestionResult(2l, user, pref2, true);
		PreferenceQuestionResult q3 = new PreferenceQuestionResult(3l, user, pref3, false);
		kieSession.insert(q1);
		kieSession.insert(q2);
		kieSession.insert(q3);
		
		Set<Preference> preferences = new HashSet<Preference>();
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		Preference pref4 = new Preference(4l, "Preference 4", true, field);
		preferences.add(pref4);
		
		Profession profession = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		profession.setActivities(preferences);
		kieSession.insert(profession);
		
		kieSession.getAgenda().getAgendaGroup("suitableProfessionsTest-preferences").setFocus();
		Integer fired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Find candidate professions based on preferences"));
		assertEquals((Integer)1, fired);
		assertEquals( 2, profSuitList.getSuitableProfessions().get(0).getMatching().getPreferences().size());
		
	}
}
