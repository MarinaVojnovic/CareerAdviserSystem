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
import com.sbnz.career.adviser.events.SuspiciousEvent;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

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
	public void userUnsuccessfullLoggedThreeTimes() {
		User user = new User();
		user.setId(1l);
		user.setAllowed(true);
		LoginEvent e1 = new LoginEvent(user, false);
		LoginEvent e2 = new LoginEvent(user, false);
		LoginEvent e3 = new LoginEvent(user, false);
		kieSession.insert(e1);
		kieSession.insert(e2);
		kieSession.insert(e3);
		
		kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
		kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("More than 3 logging in unsuccessfully in 10 minutes from one user"));
	
		assertEquals(false, user.getAllowed());
	}
	
	@Test
	public void suspciousUserEventExistUnsuccessfullLogin() {
		User user = new User();
		user.setId(1l);
		user.setAllowed(true);
		LoginEvent e1 = new LoginEvent(user, false);
		kieSession.insert(e1);
		SuspiciousEvent se = new SuspiciousEvent(user, "Maximum number of atempts reached");
		kieSession.insert(se);
		kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer)1, fired);
	}
	
	@Test
	public void suspciousUserEventExistSuccessfullLogin() {
		User user = new User();
		user.setId(1l);
		user.setAllowed(true);
		LoginEvent e1 = new LoginEvent(user, true);
		kieSession.insert(e1);
		SuspiciousEvent se = new SuspiciousEvent(user, "Maximum number of atempts reached");
		kieSession.insert(se);
		kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer)1, fired);
	}
	
	@Test
	public void successfullLogin() {
		User user = new User();
		user.setId(1l);
		user.setAllowed(false);
		LoginEvent e1 = new LoginEvent(user, true);
		kieSession.insert(e1);
		kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer)1, fired);
		assertEquals(true, user.getAllowed());
		
	}
	
}
