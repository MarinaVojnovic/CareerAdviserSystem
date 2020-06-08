package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.time.SessionPseudoClock;
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

import com.sbnz.career.adviser.entity.TestsDoneInDay;
import com.sbnz.career.adviser.events.TestDoneEvent;
import com.sbnz.career.adviser.model.Counter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportTest {

	@Autowired
	private KieContainer kieContainer;
	
	private KieSession kieSession;
	
	
	/*
	@Before
    public void before() {
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		kieSession = kbase.newKieSession();
	}
	*/
	
	@Test
	public void calculateNumberOfTestsDone() {
		TestDoneEvent e1 = new TestDoneEvent();
		TestDoneEvent e2 = new TestDoneEvent();
		TestDoneEvent e3 = new TestDoneEvent();
		TestsDoneInDay report= new TestsDoneInDay(1l, LocalDate.now(), 0l);
		Counter counter = new Counter();
		KieSession kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
		kieSession.insert(e1);
		kieSession.insert(e2);
		kieSession.insert(e3);
	
		kieSession.insert(report);
		kieSession.insert(counter);
		
		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		kieSession.fireAllRules();
		assertEquals((Long) 3l, report.getNumber());
		
		
		SessionPseudoClock clock = kieSession.getSessionClock();
		clock.advanceTime(23, TimeUnit.HOURS);
		
		kieSession.fireAllRules();
		assertEquals((Long)3l, report.getNumber());
		clock.advanceTime(3, TimeUnit.HOURS);
		kieSession.fireAllRules();
		
		
		
		
	}
}
