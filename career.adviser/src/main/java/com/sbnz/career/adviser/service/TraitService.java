package com.sbnz.career.adviser.service;

import java.util.List;

import com.sbnz.career.adviser.entity.Trait;

public interface TraitService {

	List<Trait> getAll();
	void create(Trait trait);
	List<Trait> getByPersonalityField(String persField);
	Boolean newTest();
}
