package com.sbnz.career.adviser.tests;


import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;

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

import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.model.TraitQuestionResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalityTest {
	
	  	@Autowired
		private KieContainer kieContainer;
		
		private KieSession kieSession;
		
		private TraitsResult traitsResult;
		
		private TraitQuestionResult traitQuestionResult;
		
		private TraitQuestion tq1;
		
		private TraitQuestion tq2;
		
		private TraitQuestion tq3;
		
		private TraitQuestion tq4;
		
		private TraitQuestion tq5;
		
		private TraitQuestion tq6;
		
		private TraitQuestion tq7;
		
		private TraitQuestion tq8;
		
		private TraitQuestion tq9;
		
		private TraitQuestion tq10;
		
		
		@Before
	    public void before() {
	
			traitsResult = new TraitsResult();
			traitQuestionResult=new TraitQuestionResult();
			KieServices ks = KieServices.Factory.get();
			KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
			kbconf.setOption(EventProcessingOption.STREAM);
			KieBase kbase = kieContainer.newKieBase(kbconf);
			kieSession = kbase.newKieSession();
			kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
			kieSession.insert(traitsResult);			
			tq1 = new TraitQuestion("", new Trait("mind", "extraverted"), true); 
			tq2 = new TraitQuestion("", new Trait("mind", "introverted"), true); 
			tq3 = new TraitQuestion("", new Trait("identity", "assertive"), true); 
			tq4 = new TraitQuestion("", new Trait("identity", "turbulent"), true); 
			tq5 = new TraitQuestion("", new Trait("nature", "thinking"), true); 
			tq6 = new TraitQuestion("", new Trait("nature", "feeling"), true); 
			tq7 = new TraitQuestion("", new Trait("energy", "realist"), true); 
			tq8 = new TraitQuestion("", new Trait("energy", "visionary"), true);
			tq9 = new TraitQuestion("", new Trait("tactics", "judging"), true);
			tq10 = new TraitQuestion("", new Trait("tactics", "prospecting"), true);
			
		}
		
		@Test
		public void targetExtravertedIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq1);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target extroverted, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getExtraverted());
			
		}
		
		@Test
		public void targetExtravertedIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq1);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target extroverted, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getIntroverted());
			
		}
		
		@Test
		public void targetIntrovertedIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq2);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target introverted, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getIntroverted());
			
		}
		
		@Test
		public void targetIntrovertedIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq2);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target introverted, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getExtraverted());
			
		}
		
		@Test
		public void targetAssertiveIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq3);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target assertive, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getAssertive());
			
		}
		
		@Test
		public void targetAssertiveIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq3);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target assertive, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getTurbulent());
			
		}
		
		@Test
		public void targetTurbulentIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq4);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target turbulent, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getTurbulent());
			
		}
		
		@Test
		public void targetTurbulentIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq4);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target turbulent, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getAssertive());
			
		}
		
		@Test
		public void targetThinkingIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq5);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target thinking, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getThinking());
			
		}
		
		@Test
		public void targetThinkingIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq5);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target thinking, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getFeeling());
			
		}
		
		@Test
		public void targetFeelingIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq6);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target feeling, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getFeeling());
			
		}
		
		@Test
		public void targetFeelingIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq6);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target feeling, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getThinking());
			
		}
		
		@Test
		public void targetRealistIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq7);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target realist, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getRealist());
			
		}
		
		@Test
		public void targetRealistIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq7);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target realist, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getVisionary());
			
		}
		
		@Test
		public void targetVisionaryIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq8);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target visionary, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getVisionary());
			
		}
		
		@Test
		public void targetVisionaryIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq8);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target visionary, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getRealist());
			
		}
		

		@Test
		public void targetJudgingIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq9);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target judging, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getJudging());
			
		}
		

		@Test
		public void targetJudgingIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq9);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target judging, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getProspecting());
			
		}
		
		@Test
		public void targetProspectingIsCheckedTrue() {
			traitQuestionResult.setIsChecked(true);
			traitQuestionResult.setTraitQuestion(tq10);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target prospecting, isChecked == true"));
		    assertEquals((Integer) 1, traitsResult.getProspecting());
			
		}
		
		@Test
		public void targetProspectingIsCheckedFalse() {
			traitQuestionResult.setIsChecked(false);
			traitQuestionResult.setTraitQuestion(tq10);
			this.kieSession.insert(traitQuestionResult);
			this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Traits result - target prospecting, isChecked == false"));
		    assertEquals((Integer) 1, traitsResult.getJudging());
			
		}
		
		@Test
		public void isExtraverted() {
			traitsResult.setExtraverted(10);
			traitsResult.setIntraverted(5);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating trait mind-extraverted"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("mind",trait.getPersonalityField());
		    assertEquals("extraverted",trait.getTarget());
		}
		
		@Test
		public void isIntroverted() {
			traitsResult.setExtraverted(5);
			traitsResult.setIntraverted(10);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating trait mind-introverted"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("mind",trait.getPersonalityField());
		    assertEquals("introverted",trait.getTarget());
		}
		
		@Test
		public void isAssertive() {
			traitsResult.setAssertive(10);
			traitsResult.setTurbulent(5);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating identity-assertive"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("identity",trait.getPersonalityField());
		    assertEquals("assertive",trait.getTarget());
		}
		
		@Test
		public void isTurbulent() {
			traitsResult.setAssertive(5);
			traitsResult.setTurbulent(10);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating identity-turbulent"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("identity",trait.getPersonalityField());
		    assertEquals("turbulent",trait.getTarget());
		}
		
		@Test
		public void isThinking() {
			traitsResult.setThinking(10);
			traitsResult.setFeeling(5);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating nature-thinking"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("nature",trait.getPersonalityField());
		    assertEquals("thinking",trait.getTarget());
		}
		
		@Test
		public void isFeeling() {
			traitsResult.setThinking(5);
			traitsResult.setFeeling(10);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating nature-feeling"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("nature",trait.getPersonalityField());
		    assertEquals("feeling",trait.getTarget());
		}
		
		@Test
		public void isRealist() {
			traitsResult.setRealist(10);
			traitsResult.setVisionary(5);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating energy-realist"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("energy",trait.getPersonalityField());
		    assertEquals("realist",trait.getTarget());
		}
		
		@Test
		public void isVisionary() {
			traitsResult.setRealist(5);
			traitsResult.setVisionary(10);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating energy-visionary"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("energy",trait.getPersonalityField());
		    assertEquals("visionary",trait.getTarget());
		}
		
		@Test
		public void isJudging() {
			traitsResult.setJudging(10);
			traitsResult.setProspecting(5);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating tactics-judging"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("tactics",trait.getPersonalityField());
		    assertEquals("judging",trait.getTarget());
		}
		
		@Test
		public void isProspecting() {
			traitsResult.setJudging(5);
			traitsResult.setProspecting(10);
			kieSession.insert(traitsResult);
			int fired = this.kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Creating tactics-prospecting"));
			Collection<?> newObjects = kieSession.getObjects(new ClassObjectFilter(Trait.class));
			Iterator it = newObjects.iterator();
		    assertEquals(1, fired);
		    assertEquals(1, newObjects.size());
		    Trait trait = (Trait) it.next();
		    assertEquals("tactics",trait.getPersonalityField());
		    assertEquals("prospecting",trait.getTarget());
		}
		
	  @After
	    public void disposeOfSession() {
	        this.kieSession.dispose();
	    }
		
}
