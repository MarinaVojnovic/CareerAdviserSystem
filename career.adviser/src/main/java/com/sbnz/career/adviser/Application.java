package com.sbnz.career.adviser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbnz.career.adviser.entity.Preference;
import com.sbnz.career.adviser.entity.PreferenceQuestionResult;
import com.sbnz.career.adviser.entity.Profession;
import com.sbnz.career.adviser.entity.Trait;
import com.sbnz.career.adviser.entity.TraitQuestion;
import com.sbnz.career.adviser.entity.TraitsResult;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.enums.EnergyEnum;
import com.sbnz.career.adviser.enums.IdentityEnum;
import com.sbnz.career.adviser.enums.MindEnum;
import com.sbnz.career.adviser.enums.NatureEnum;
import com.sbnz.career.adviser.enums.TacticsEnum;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.Matching;
import com.sbnz.career.adviser.model.PossibleProfession;
import com.sbnz.career.adviser.model.ProfessionsSuitabilityList;
import com.sbnz.career.adviser.model.RecommendedProfessions;
import com.sbnz.career.adviser.model.TraitQuestionResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
public class Application {


	
	@Bean
    public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//System.out.println("Hello world");
		
		//personalityTest();
		//matchingTest();
		//findCandidateProfessions();
		
	}

	
	
	
	

	
	
    
	
}