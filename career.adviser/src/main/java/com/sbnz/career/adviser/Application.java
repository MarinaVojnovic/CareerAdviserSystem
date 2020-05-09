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

import com.sbnz.career.adviser.model.Activity;
import com.sbnz.career.adviser.model.Criteriums;
import com.sbnz.career.adviser.model.PossibleProfession;
import com.sbnz.career.adviser.model.PreferenceQuestionResult;
//import com.sbnz.career.adviser.model.Personality;
import com.sbnz.career.adviser.model.Profession;
import com.sbnz.career.adviser.model.RecommendedProfessions;
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
	
		
		/*
		Set<Trait> traits1 = new HashSet<Trait>();
		traits1.add(new Trait("mind", "extraverted"));
		traits1.add(new Trait("identity", "assertive"));
		traits1.add(new Trait("nature", "feeling"));
		traits1.add(new Trait("energy", "visionary"));
		traits1.add(new Trait("tactics", "prospecting"));
		*/
		
		
		//PRAVILO 2
		//ovde nije zadovoljeno sve ali je bolje od dva
		Set<Trait> traits1 = new HashSet<Trait>();
		/*
		traits1.add(new Trait("mind", "extraverted"));
		traits1.add(new Trait("identity", "assertive"));
		traits1.add(new Trait("nature", "feeling"));
		traits1.add(new Trait("energy", "visionary"));
		traits1.add(new Trait("tactics", "judging"));
		*/
		//Ovde je sad zad 4/5 i to je nije sve ali je vece od max ->pravilo 2
		traits1.add(new Trait("mind", "extraverted"));
		traits1.add(new Trait("identity", "assertive"));
		traits1.add(new Trait("nature", "feeling"));
		traits1.add(new Trait("energy", "visionary"));
		traits1.add(new Trait("tactics", "judging"));

		Activity act1 = new Activity(1l, "Working on computer", true);
		Activity act2 = new Activity(2l, "Keeping things in order.", true);
		Activity act3 = new Activity(3l, "Comparing prices and buying where it is cheaper.", true);
		Activity act4 = new Activity(4l, "Having order or system in my activities.", true);
		Activity act5 = new Activity(5l, "Saving money and thinking carefully how to use it. ", true);
		Set<Activity> activities1 = new HashSet<Activity>();
		activities1.add(act1);
		activities1.add(act2);
		activities1.add(act3);
		activities1.add(act4);
		activities1.add(act5);
		Profession prof1 = new Profession(1L, "Administration", activities1, traits1, ProfessionalField.ECNONOMY, "Rad sa papirima", true, 100000, 50);
		Set<Profession> professions1 = new HashSet<Profession>();
		professions1.add(prof1);
		act1.setProfessions(professions1);
		act2.setProfessions(professions1);
		act3.setProfessions(professions1);
		act4.setProfessions(professions1);
		act5.setProfessions(professions1);
		//PreferenceQuestionResult(Long id, Long userId, Activity activity, Boolean isChecked)
		PreferenceQuestionResult prefQuestResult1 = new PreferenceQuestionResult(1l, 1l, act1, true);
		PreferenceQuestionResult prefQuestResult2 = new PreferenceQuestionResult(2l, 1l, act2, true);
		PreferenceQuestionResult prefQuestResult3 = new PreferenceQuestionResult(3l, 1l, act3, true);
		PreferenceQuestionResult prefQuestResult4 = new PreferenceQuestionResult(4l, 1l, act4, true);
		PreferenceQuestionResult prefQuestResult5 = new PreferenceQuestionResult(5l, 1l, act5, false);
		kieSession.insert(prefQuestResult1);
		kieSession.insert(prefQuestResult2);
		kieSession.insert(prefQuestResult3);
		kieSession.insert(prefQuestResult4);
		kieSession.insert(prefQuestResult5);
		
		
		//PRAVILO 0 ->3/3
		Set<Trait> traits2 = new HashSet<Trait>();
		//Ovde je zadovoljeno sve ali samo dva -> 1.0
		//traits2.add(new Trait("mind", "introverted"));
		//traits2.add(new Trait("identity", "turbulent"));
		//max zadovoljenih
		traits2.add(new Trait("mind", "extraverted"));
		traits2.add(new Trait("nature", "feeling"));
		traits2.add(new Trait("energy", "visionary"));
		//traits2.add(new Trait("tactics", "prospecting"));
		Activity act21 = new Activity(6l, "Learning on your own", true);
		Activity act22 = new Activity(7l, "Having no life.", true);
		Activity act222 = new Activity(100l, "Some activity.", true);
		Set<Activity> activities2 = new HashSet<Activity>();
		activities2.add(act21);
		activities2.add(act22);
		activities2.add(act222);
		Profession prof2 = new Profession(2L, "Programming", activities2, traits2, ProfessionalField.ENGINEERING, "Rad sa kompom", true, 90000, 40);
		Set<Profession> professions2 = new HashSet<Profession>();
		professions2.add(prof2);
		act21.setProfessions(professions2);
		act22.setProfessions(professions2);
		PreferenceQuestionResult prefQuestResult21 = new PreferenceQuestionResult(6l, 1l, act21, true);
		PreferenceQuestionResult prefQuestResult22 = new PreferenceQuestionResult(7l, 1l, act22, true);
		PreferenceQuestionResult prefQuestResult222 = new PreferenceQuestionResult(100l, 1l, act222, true);
		kieSession.insert(prefQuestResult21);
		kieSession.insert(prefQuestResult22);
		kieSession.insert(prefQuestResult222);
		
		
		//PRAVILO 1
		//Ovde je zad jedan od pet -> 0.2
		Set<Trait> traits3 = new HashSet<Trait>();
		traits3.add(new Trait("mind", "extraverted"));
		//traits3.add(new Trait("identity", "assertive"));
		//traits3.add(new Trait("nature", "thinking"));
		//traits3.add(new Trait("energy", "visionary"));
		//traits3.add(new Trait("tactics", "prospecting"));
		//traits3.add(new Trait("tactics", "judging"));//manje od max
		Activity act31 = new Activity(8l, "Enjoy drawing something for your own pleasure.", true);
		Activity act32 = new Activity(9l, "Being able to clearly communicate own but as well understand ideas of others.", true);
		Activity act33 = new Activity(10l, "Keeping track of world’s trends and modern design achievements..", true);
		Activity act34 = new Activity(11l, "Eager for constant improvement.", true);
		Set<Activity> activities3 = new HashSet<Activity>();
		activities3.add(act31);
		/*
		activities3.add(act32);
		activities3.add(act33);
		activities3.add(act34);
		*/
		Profession prof3 = new Profession(3L, "Graficki dizajn", activities3, traits3, ProfessionalField.ENGINEERING, "Graficki dizajn", true, 80000, 30);
		Set<Profession> professions3 = new HashSet<Profession>();
		professions3.add(prof3);
		act31.setProfessions(professions3);
		act32.setProfessions(professions3);
		act33.setProfessions(professions3);
		act34.setProfessions(professions3);
		PreferenceQuestionResult prefQuestResult31 = new PreferenceQuestionResult(8l, 1l, act31, true);
		/*
		PreferenceQuestionResult prefQuestResult32 = new PreferenceQuestionResult(9l, 1l, act32, true);
		PreferenceQuestionResult prefQuestResult33 = new PreferenceQuestionResult(10l, 1l, act33, true);
		PreferenceQuestionResult prefQuestResult34 = new PreferenceQuestionResult(10l, 1l, act34, false);
		*/
		kieSession.insert(prefQuestResult31);
		/*
		kieSession.insert(prefQuestResult32);
		kieSession.insert(prefQuestResult33);
		kieSession.insert(prefQuestResult34);
		*/
		
		/*
		Set<Trait> traits1 = new HashSet<Trait>();
		traits1.add(new Trait("mind", "extraverted"));
		traits1.add(new Trait("identity", "assertive"));
		traits1.add(new Trait("nature", "feeling"));
		traits1.add(new Trait("energy", "visionary"));
		traits1.add(new Trait("tactics", "prospecting"));
		*/
	
		//Ovde je zad 1/2 -> pravilo 3
		//PRAVILO 3
		Set<Trait> traits4 = new HashSet<Trait>();
		traits4.add(new Trait("mind", "extraverted"));
		traits4.add(new Trait("identity", "turbulent"));
		traits4.add(new Trait("nature", "feeling"));//oki
		//traits4.add(new Trait("energy", "realist"));
		//traits4.add(new Trait("tactics", "prospecting"));//oki
		Activity act41 = new Activity(12l, "Would enjoy profession that involves counselling others to solve their problems.", true);
		Activity act42 = new Activity(13l, "Enjoy listening, observing and studying people.", true);
		Activity act43 = new Activity(14l, "Would consider yourself as highly emotionally intellectual person.", true);
		Set<Activity> activities4 = new HashSet<Activity>();
		activities4.add(act41);
		activities4.add(act42);
		activities4.add(act43);
		Profession prof4 = new Profession(4L, "Pshycology", activities4, traits4, ProfessionalField.ENGINEERING, "Psychology", true, 70000, 20);
		Set<Profession> professions4 = new HashSet<Profession>();
		professions4.add(prof4);
		act41.setProfessions(professions4);
		act42.setProfessions(professions4);
		act43.setProfessions(professions4);
		PreferenceQuestionResult prefQuestResult41 = new PreferenceQuestionResult(8l, 1l, act41, true);
		PreferenceQuestionResult prefQuestResult42 = new PreferenceQuestionResult(9l, 1l, act42, false);
		PreferenceQuestionResult prefQuestResult43 = new PreferenceQuestionResult(10l, 1l, act43, false);
		kieSession.insert(prefQuestResult41);
		kieSession.insert(prefQuestResult42);
		kieSession.insert(prefQuestResult43);
	
		kieSession.insert(prof1);
		kieSession.insert(prof2);
		kieSession.insert(prof3);
		kieSession.insert(prof4);
		
		kieSession.getAgenda().getAgendaGroup("traitsTest").setFocus();
		kieSession.fireAllRules();
		
		
		Criteriums criteriums = new Criteriums(true, true, true, true);
		kieSession.insert(criteriums);
		kieSession.getAgenda().getAgendaGroup("preferencesTest").setFocus();
		kieSession.fireAllRules();
		
		
		
		kieSession.insert(criteriums);
		kieSession.getAgenda().getAgendaGroup("paymentTest").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("employmentTest").setFocus();
		kieSession.fireAllRules();
		
		RecommendedProfessions recommendedProfessions = new RecommendedProfessions();
		kieSession.insert(recommendedProfessions);
		
			
		kieSession.getAgenda().getAgendaGroup("results").setFocus();
		kieSession.fireAllRules();
		
		for (PossibleProfession possibleProf : recommendedProfessions.getProfessions()) {
			System.out.println(possibleProf.getProfession().getName()+" "+possibleProf.getScore());
		}
		
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
		kieSession.getAgenda().getAgendaGroup("personalityTest").setFocus();
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