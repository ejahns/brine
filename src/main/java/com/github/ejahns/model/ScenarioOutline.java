package com.github.ejahns.model;

import com.github.ejahns.Token;

public class ScenarioOutline extends AbstractScenario {

	@Override
	public boolean add(GherkinElement t) {
		return false;
	}

	@Override
	public boolean consume(Token t) {
		return false;
	}
}
