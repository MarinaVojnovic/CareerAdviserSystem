package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

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

import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.events.LoginEvent;
import com.sbnz.career.adviser.events.NewPersonalityTestEvent;
import com.sbnz.career.adviser.events.SuspiciousEvent;
import com.sbnz.career.adviser.events.SuspiciousPersonalityTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewPersonalityTest {

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
	public void tooManyAtemptsInsertingSuspiciousEvent() {
		User user = new User();
		user.setId(1l);
		user.setNewPersTest(true);
		NewPersonalityTestEvent e1= new NewPersonalityTestEvent(user);
		NewPersonalityTestEvent e2= new NewPersonalityTestEvent(user);
		NewPersonalityTestEvent e3= new NewPersonalityTestEvent(user);
		kieSession.insert(e1);
		kieSession.insert(e2);
		kieSession.insert(e3);
		kieSession.getAgenda().getAgendaGroup("newPersonalityTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("More than 1 attempt for redoing personality test in 2 minutes"));
		assertEquals(false, user.getNewPersTest());
	}
	
	@Test
	public void suspciousUserEventExistUnsuccessfull() {
		User user = new User();
		user.setId(1l);
		user.setNewPersTest(false);
		NewPersonalityTestEvent e1= new NewPersonalityTestEvent(user);
		kieSession.insert(e1);
		SuspiciousPersonalityTest se = new SuspiciousPersonalityTest(user, "Maximum number of tests in two minutes reached.");
		kieSession.insert(se);
		kieSession.getAgenda().getAgendaGroup("newPersonalityTest").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer)1, fired);
	}
	
	@Test
	public void noSuspciousUserEventExistSuccessfull() {
		User user = new User();
		user.setId(1l);
		user.setNewPersTest(false);
		NewPersonalityTestEvent e1= new NewPersonalityTestEvent(user);
		kieSession.insert(e1);
		kieSession.getAgenda().getAgendaGroup("newPersonalityTest").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer)1, fired);
		assertEquals(true, user.getNewPersTest());
	}
	
	@Test
	public void suspciousUserEventExistButLessThenThreeAtemptsSuccessfull() {
		User user = new User();
		user.setId(1l);
		user.setNewPersTest(false);
		NewPersonalityTestEvent e1= new NewPersonalityTestEvent(user);
		kieSession.insert(e1);
		SuspiciousPersonalityTest se = new SuspiciousPersonalityTest(user, "Maximum number of tests in two minutes reached.");
		kieSession.insert(se);
		kieSession.getAgenda().getAgendaGroup("newPersonalityTest").setFocus();
		Integer fired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Less than 3 logging in unsuccessfully in 10 minutes from one user"));
		assertEquals((Integer)1, fired);
		assertEquals(true, user.getNewPersTest());
	}
}
