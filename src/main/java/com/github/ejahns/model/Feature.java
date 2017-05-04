package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.Token;

public class Feature implements GherkinElement {

	private List<String> tags = new ArrayList<>();
	private String featureName;
	private List<String> description = new ArrayList<>();
	private Background background;
	private List<ScenarioDefinition> definitions = new ArrayList<>();

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof ScenarioDefinition) {
			definitions.add((ScenarioDefinition) t);
			return true;
		} else if (t instanceof Background) {
			background = (Background) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case OtherToken:
				description.add(t.getLine());
				return true;
			case TagLineToken:
				tags.add(t.getLine());
				return true;
			case FeatureLineToken:
				featureName = t.getLine();
				return true;

		}
		return false;
	}
}
