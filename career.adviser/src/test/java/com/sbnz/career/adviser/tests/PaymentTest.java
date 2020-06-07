package com.sbnz.career.adviser.tests;

import static org.junit.Assert.assertEquals;

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

import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.PossibleProfession;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentTest {

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
	public void possibleProfessionAlreadyExists() {
		
		PossibleProfession possibleProfession1 = new PossibleProfession();
		Profession profession1 = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		possibleProfession1.setProfession(profession1);
		
		
		PossibleProfession possibleProfession2 = new PossibleProfession();
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 20000, 15, "", 0d);
		possibleProfession2.setProfession(profession2);
		
		System.out.println("Payment 1 : "+profession1.getPayment());
		System.out.println("Payment 2: "+profession2.getPayment());
		
		kieSession.insert(possibleProfession1);
		kieSession.insert(possibleProfession2);
		
		kieSession.getAgenda().getAgendaGroup("paymentTest").setFocus();
		kieSession.fireAllRules();
		//new RuleNameEqualsAgendaFilter("If there are possible professions inserted, caluculate influence of employment")
	    assertEquals((Double) 1.0, possibleProfession2.getScore());
	    assertEquals((Double) (1.0*profession1.getPayment()/profession2.getPayment()), possibleProfession1.getScore());
	  
	}
	
	@Test
	public void possibleProfessionsNotExist() {
		Criteriums criteriums = new Criteriums(false, false, true, false);
		Profession profession1 = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0.5);
		Profession profession2 = new Profession(2l, "Prof B", null, null, null, true, 20000, 15, "", 0d);
		Profession profession3 = new Profession(3l, "Prof C", null, null, null, true, 20000, 15, "", 0d);
		kieSession.insert(profession1);
		kieSession.insert(profession2);
		kieSession.insert(profession3);
		kieSession.insert(criteriums);
		
		kieSession.getAgenda().getAgendaGroup("paymentTest").setFocus();
		Integer fired = kieSession.fireAllRules();
		assertEquals((Integer) 3, fired);
		
	}
	
	@After
    public void disposeOfSession() {
		this.kieSession.dispose();
    }
}
