package com.sbnz.career.adviser;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoginKieSessionConfig {


	private KieContainer kieContainer;

	public LoginKieSessionConfig(KieContainer kieContainer) {
		System.out.println("Constructor called");
		this.kieContainer=kieContainer;
	}


	//@Bean(name="loginKieSession")
	@Bean(name="loginKieSession")
	public KieSession loginKieSession() {
		System.out.println("**********************");
		System.out.println("**********************");
		System.out.println("login kiew sesssioooooooooooon");
		System.out.println("**********************");
		System.out.println("**********************");
		//return kieContainer.newKieSession("someSession");
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);
		KieSession kieSession = kbase.newKieSession();
		//kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
		return kieSession;
	}
	
	//@Bean(name="loginKieSession")
		@Bean(name="newPersTestSession")
		public KieSession newPersTest() {
			System.out.println("**********************");
			System.out.println("**********************");
			System.out.println("login kiew sesssioooooooooooon");
			System.out.println("**********************");
			System.out.println("**********************");
			//return kieContainer.newKieSession("someSession");
			KieServices ks = KieServices.Factory.get();
			KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
			kbconf.setOption(EventProcessingOption.STREAM);
			KieBase kbase = kieContainer.newKieBase(kbconf);
			KieSession kieSession = kbase.newKieSession();
			//kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
			return kieSession;
		}
	

	
	
}
