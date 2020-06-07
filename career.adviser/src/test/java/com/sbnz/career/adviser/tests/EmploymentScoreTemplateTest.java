 package com.sbnz.career.adviser.tests;



import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.builder.Message;

import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.career.adviser.entity.EmploymentScoreTemplate;
import com.sbnz.career.adviser.entity.Profession;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmploymentScoreTemplateTest {

	 @Test
	    public void testSimpleTemplateWithObjects(){
	        
			InputStream template = EmploymentScoreTemplateTest.class.getResourceAsStream("/templates/employmentScore.drt");
		        
			List<EmploymentScoreTemplate> data = new ArrayList<EmploymentScoreTemplate>();
			
			data.add(new EmploymentScoreTemplate(0, 10, 0.25));
			data.add(new EmploymentScoreTemplate(11, 20, 0.5));
			data.add(new EmploymentScoreTemplate(21, 30, 0.75));
			data.add(new EmploymentScoreTemplate(31, 100, 1.0));
			
			ObjectDataCompiler converter = new ObjectDataCompiler();
			String drl = converter.compile(data, template);
			
			System.out.println(drl);
			KieSession ksession = createKieSessionFromDRL(drl);
			
			Profession p1 = new Profession(1l, "Prof A", null, null, null, true, 10000, 5, "", 0d);
			Profession p2 = new Profession(2l, "Prof B", null, null, null, true, 10000, 15, "", 0d);
			Profession p3 = new Profession(3l, "Prof C", null, null, null, true, 10000, 25, "", 0d);
			Profession p4 = new Profession(4l, "Prof D", null, null, null, true, 10000, 50, "", 0d);
		
			
			ksession.insert(p1);
			ksession.insert(p2);
			ksession.insert(p3);
			ksession.insert(p4);
			
			ksession.fireAllRules();
			ksession.dispose();
			
			assertEquals((Double) 0.25, p1.getEmploymentScore());
			assertEquals((Double) 0.5, p2.getEmploymentScore());
			assertEquals((Double)0.75, p3.getEmploymentScore());
			assertEquals((Double) 1.0, p4.getEmploymentScore());
	 }
			
			private KieSession createKieSessionFromDRL(String drl){
		        KieHelper kieHelper = new KieHelper();
		        kieHelper.addContent(drl, ResourceType.DRL);
		        
		        Results results = kieHelper.verify();
		        
		        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
		            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
		            for (Message message : messages) {
		                System.out.println("Error: "+message.getText());
		            }
		            
		            throw new IllegalStateException("Compilation errors were found. Check the logs.");
		        }
		        
		        return kieHelper.build().newKieSession();
		    }
	
}
