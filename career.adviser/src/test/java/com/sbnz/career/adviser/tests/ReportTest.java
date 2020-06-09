package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;

import org.drools.core.ClockType;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.career.adviser.entity.TestsDoneInDay;
import com.sbnz.career.adviser.events.TestDoneEvent;
import com.sbnz.career.adviser.model.Counter;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.builder.Message;

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
		/*
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("drools/spring/rules/report.drl"));
        KieBuilder kbuilder = ks.newKieBuilder(kfs);
        kbuilder.buildAll();
        if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Coudln't build knowledge module" + kbuilder.getResults());
        }
        KieContainer kContainer = ks.newKieContainer(kbuilder.getKieModule().getReleaseId());
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);
        KieBase kbase = kContainer.newKieBase(kbconf);
        */
		
		KieSession kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
		/*
        KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
        ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kieSession = kbase.newKieSession(ksconf, null);
        */
		kieSession.insert(report);
		kieSession.insert(e1);
		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		kieSession.fireAllRules();
		assertEquals((Long)1l, report.getNumber());
		kieSession.insert(e2);
		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		kieSession.fireAllRules();
		assertEquals((Long)2l, report.getNumber());
		kieSession.insert(e3);
		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		kieSession.fireAllRules();
	
		
		//kieSession.insert(counter);

		SessionPseudoClock clock = kieSession.getSessionClock();
		clock.advanceTime(25, TimeUnit.HOURS);
		
		kieSession.fireAllRules();
		assertEquals((Long)0l, report.getNumber());
		
		
		
		
		/*
		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		kieSession.fireAllRules();
		assertEquals((Long) 3l, report.getNumber());
		*/
		
		
		
		
		
		
		
	}
}
