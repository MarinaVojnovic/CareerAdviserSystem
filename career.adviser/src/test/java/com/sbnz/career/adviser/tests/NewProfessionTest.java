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
import com.sbnz.career.adviser.events.NewPersonalityTestEvent;
import com.sbnz.career.adviser.events.NewProfessionTestEvent;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewProfessionTest {

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
		user.setNewProfTest(true);
		NewProfessionTestEvent e1= new NewProfessionTestEvent(user);
		NewProfessionTestEvent e2= new NewProfessionTestEvent(user);
		NewProfessionTestEvent e3= new NewProfessionTestEvent(user);
		NewProfessionTestEvent e4= new NewProfessionTestEvent(user);
		kieSession.insert(e1);
		kieSession.insert(e2);
		kieSession.insert(e3);
		kieSession.insert(e4);
		kieSession.getAgenda().getAgendaGroup("newProfessionTest").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("More than 1 attempt for redoing profession test in 1m"));
		assertEquals(false, user.getNewProfTest());
	}
	


	
	@Test
	public void suspciousUserEventExistButLessThenThreeAtemptsSuccessfull() {
		User user = new User();
		user.setId(1l);
		user.setNewProfTest(false);
		NewProfessionTestEvent e1= new NewProfessionTestEvent(user);
		kieSession.insert(e1);
	;
		kieSession.getAgenda().getAgendaGroup("newProfessionTest").setFocus();
		Integer fired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("Allowed to do new profession test"));
		assertEquals((Integer)1, fired);
		assertEquals(true, user.getNewProfTest());
	}
}
