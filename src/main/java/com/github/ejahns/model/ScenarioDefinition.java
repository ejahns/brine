package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.Token;
import com.google.gson.annotations.Expose;

public class ScenarioDefinition implements GherkinElement {

	@Expose
	private List<String> tags = new ArrayList<>();
	private Scenario scenario = null;
	private ScenarioOutline scenarioOutline = null;

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof Scenario) {
			scenario = (Scenario) t;
			return true;
		}
		if (t instanceof ScenarioOutline) {
			scenarioOutline = (ScenarioOutline) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case TagLineToken:
				tags.add(t.getLine());
				return true;
		}
		return false;
	}
}
