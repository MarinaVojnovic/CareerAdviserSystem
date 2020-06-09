package com.sbnz.career.adviser.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.TestsDoneInDay;
import com.sbnz.career.adviser.repository.TestsDoneInDayRepository;
import com.sbnz.career.adviser.service.TestsDoneInDayService;

@Service
public class TestsDoneInDayServiceImpl implements TestsDoneInDayService{

	private TestsDoneInDayRepository testsDoneInDayRepo;
	
	private KieSession kieSession;
	public TestsDoneInDayServiceImpl(@Qualifier("reportSession") KieSession kieSession, TestsDoneInDayRepository testsDoneInDayRepo) {
		this.testsDoneInDayRepo=testsDoneInDayRepo;
		this.kieSession=kieSession;
	}

	@Override
	public List<TestsDoneInDay> getReport() {
		System.out.println("Get report called from service.");
		return this.testsDoneInDayRepo.findAll();
	}
	
	@Scheduled(cron="0 8 * * * *")
	public void fireReportRules() {
		TestsDoneInDay report = new TestsDoneInDay(LocalDate.now());
		kieSession.insert(report);
		kieSession.fireAllRules();
		System.out.println("Number of tests done in previous day: "+report.getNumber());
		this.testsDoneInDayRepo.save(report);
	}
	
}
