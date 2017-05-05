package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.Token;

public class Feature implements GherkinElement {

	private String location;
	private int lineNum;
	private List<String> tags = new ArrayList<>();
	private String featureName;
	private List<String> description = new ArrayList<>();
	private Background background;
	private List<AbstractScenario> scenarios = new ArrayList<>();

	public String getLocation() {
		return location;
	}

	//TODO don't expose this method
	public void setLocation(String location) {
		this.location = location;
	}

	public int getLineNum() {
		return lineNum;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getFeatureName() {
		return featureName;
	}

	public List<String> getDescription() {
		return description;
	}

	public Background getBackground() {
		return background;
	}

	public List<AbstractScenario> getScenarios() {
		return scenarios;
	}

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof AbstractScenario) {
			scenarios.add((AbstractScenario) t);
			return true;
		}
		else if (t instanceof Background) {
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
				lineNum = t.getLineNum();
				featureName = t.getLine();
				return true;
			case EOFToken:
				return true;
		}
		return false;
	}
}
