package com.sbnz.career.adviser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.EmploymentScoreTemplate;


public interface EmploymentScoreTemplateService {

	public void loadTemplates();
	public void newTemplates(List<EmploymentScoreTemplate> templates);
	public List<EmploymentScoreTemplate> getAll();
}
