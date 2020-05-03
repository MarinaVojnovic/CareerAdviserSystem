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

//import com.sbnz.career.adviser.model.Personality;
import com.sbnz.career.adviser.model.Profession;
import com.sbnz.career.adviser.model.Trait;
import com.sbnz.career.adviser.model.TraitQuestion;
import com.sbnz.career.adviser.model.TraitQuestionResult;
import com.sbnz.career.adviser.model.TraitsResult;
import com.sbnz.career.enums.EnergyEnum;
import com.sbnz.career.enums.IdentityEnum;
import com.sbnz.career.enums.MindEnum;
import com.sbnz.career.enums.NatureEnum;
import com.sbnz.career.enums.ProfessionalField;
import com.sbnz.career.enums.TacticsEnum;



@SpringBootApplication
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Hello world");
		
		personalityTest();
		
	}
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
	
	public static void insertingProfessions(KieSession kieSession) {
	
		
		
		Set<Trait> traits1 = new HashSet<Trait>();
		traits1.add(new Trait("mind", "extraverted"));
		traits1.add(new Trait("identity", "assertive"));
		traits1.add(new Trait("nature", "feeling"));
		traits1.add(new Trait("energy", "realist"));
		traits1.add(new Trait("tactics", "judging"));
		Profession prof1 = new Profession(1L, "Administration", null, traits1, ProfessionalField.ECNONOMY, "Rad sa papirima", true);
		
		
		Set<Trait> traits2 = new HashSet<Trait>();
		traits2.add(new Trait("mind", "introverted"));
		traits2.add(new Trait("identity", "turbulent"));
		traits2.add(new Trait("nature", "feeling"));
		traits2.add(new Trait("energy", "visionary"));
		traits2.add(new Trait("tactics", "prospecting"));
		Profession prof2 = new Profession(2L, "Programming", null, traits2, ProfessionalField.ENGINEERING, "Rad sa kompom", true);
		
		Set<Trait> traits3 = new HashSet<Trait>();
		traits3.add(new Trait("mind", "introverted"));
		traits3.add(new Trait("identity", "turbulent"));
		traits3.add(new Trait("nature", "thinking"));
		traits3.add(new Trait("energy", "visionary"));
		traits3.add(new Trait("tactics", "prospecting"));
		Profession prof3 = new Profession(3L, "Acting", null, traits3, ProfessionalField.ENGINEERING, "Gluma", true);
				
		Set<Trait> traits4 = new HashSet<Trait>();
		traits4.add(new Trait("mind", "introverted"));
		traits4.add(new Trait("identity", "turbulent"));
		traits4.add(new Trait("nature", "feeling"));
		traits4.add(new Trait("energy", "realist"));
		traits4.add(new Trait("tactics", "prospecting"));
		Profession prof4 = new Profession(4L, "Programming", null, traits4, ProfessionalField.ENGINEERING, "Rad sa kompom", true);
		
		kieSession.insert(prof1);
		kieSession.insert(prof2);
		kieSession.insert(prof3);
		kieSession.insert(prof4);
		
		kieSession.getAgenda().getAgendaGroup("professionsTest").setFocus();
		kieSession.fireAllRules();
		
		
	}
	public static void personalityTest() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(100_000);
		
	
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		
		List<TraitQuestionResult> traits = new ArrayList<TraitQuestionResult>();
		TraitQuestion q1 = new TraitQuestion("Pitanje za mind-extraverted", new Trait("mind", "extraverted"));
		TraitQuestionResult question = new TraitQuestionResult(q1, true);
		traits.add(question);
		
		
		TraitQuestion q11 = new TraitQuestion("Pitanje za mind-extraverted", new Trait("mind", "extraverted"));
		TraitQuestionResult question11 = new TraitQuestionResult(q11, true);
		traits.add(question11);
		
		TraitQuestion q2 = new TraitQuestion("Pitanje za mind-intraverted", new Trait("mind", "introverted"));
		TraitQuestionResult question2 = new TraitQuestionResult(q2);
		traits.add(question2);
		
		TraitQuestion q3 = new TraitQuestion("Pitanje za identity-assertive", new Trait("identity", "assertive"));
		TraitQuestionResult question3 = new TraitQuestionResult(q3);
		traits.add(question3);
		
		TraitQuestion q33 = new TraitQuestion("Pitanje za identity-assertive", new Trait("identity", "assertive"));
		TraitQuestionResult question33 = new TraitQuestionResult(q33);
		traits.add(question33);
		
		TraitQuestion q4 = new TraitQuestion("Pitanje za identity-turbulent", new Trait("identity", "turbulent"));
		TraitQuestionResult question4 = new TraitQuestionResult(q4);
		traits.add(question4);
		
		TraitQuestion q5 = new TraitQuestion("Pitanje za nature-thinking", new Trait("nature", "thinking"));
		TraitQuestionResult question5 = new TraitQuestionResult(q5);
		traits.add(question5);
		
		TraitQuestion q6 = new TraitQuestion("Pitanje za nature-feeling", new Trait("nature", "feeling"));
		TraitQuestionResult question6 = new TraitQuestionResult(q6);
		traits.add(question6);
		
		TraitQuestion q66 = new TraitQuestion("Pitanje za nature-feeling", new Trait("nature", "feeling"));
		TraitQuestionResult question66 = new TraitQuestionResult(q66);
		traits.add(question66);
		
		TraitQuestion q7 = new TraitQuestion("Pitanje za energy-realist", new Trait("energy", "realist"));
		TraitQuestionResult question7 = new TraitQuestionResult(q7);
		traits.add(question7);
		
		TraitQuestion q8 = new TraitQuestion("Pitanje za energy-visionary", new Trait("energy", "visionary"));
		TraitQuestionResult question8 = new TraitQuestionResult(q8);
		traits.add(question8);
		
		TraitQuestion q88 = new TraitQuestion("Pitanje za energy-visionary", new Trait("energy", "visionary"));
		TraitQuestionResult question88 = new TraitQuestionResult(q88);
		traits.add(question88);
		
		TraitQuestion q9 = new TraitQuestion("Pitanje za tactics-judging", new Trait("tactics", "judging"));
		TraitQuestionResult question9 = new TraitQuestionResult(q9);
		traits.add(question9);
		
		TraitQuestion q10 = new TraitQuestion("Pitanje za tactics-prospecting", new Trait("tactics", "prospecting"));
		TraitQuestionResult question10 = new TraitQuestionResult(q10);
		traits.add(question10);
		
		TraitQuestion q1010 = new TraitQuestion("Pitanje za tactics-prospecting", new Trait("tactics", "prospecting"));
		TraitQuestionResult question1010 = new TraitQuestionResult(q1010);
		traits.add(question1010);
		
		
		for (TraitQuestionResult guestionRes : traits) {
			kieSession.insert(guestionRes);
		}
		
		TraitsResult traitsResult = new TraitsResult();
		kieSession.insert(traitsResult);
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules();
		System.out.println("Traits result");
		System.out.println("Extroverted" +traitsResult.getExtraverted());
		System.out.println("Introverted" + traitsResult.getIntroverted());
		System.out.println("Assertive" + traitsResult.getAssertive());
		System.out.println("Turbulent" + traitsResult.getTurbulent());
		System.out.println("Thinking" + traitsResult.getThinking());
		System.out.println("Feeling" + traitsResult.getFeeling());
		System.out.println("Realist" + traitsResult.getRealist());
		System.out.println("Visionary" + traitsResult.getVisionary());
		System.out.println("Judging"+traitsResult.getJudging());
		System.out.println("Prospecting"+traitsResult.getProspecting());
		//kieSession.dispose();
		
		insertingProfessions(kieSession);
	}
	

	
	
    
	
}