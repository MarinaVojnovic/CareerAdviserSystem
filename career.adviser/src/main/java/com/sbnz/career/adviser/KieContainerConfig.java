package com.sbnz.career.adviser;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//@Configuration
public class KieContainerConfig {
/*
	@Bean
    public KieContainer kieContainer() {
		System.out.println("napravljenooooo");
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		//KieScanner kScanner = ks.newKieScanner(kContainer);
		//kScanner.start(10_000);
		return kContainer;
		
		
    }
    */
}
/*
@Configuration
public class KieSessionConfig {

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
}
*/