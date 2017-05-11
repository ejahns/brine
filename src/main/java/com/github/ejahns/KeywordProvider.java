package com.github.ejahns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class KeywordProvider {

	private final String language;
	private final Map<String, List<String>> keywords;

	KeywordProvider(String language, Map<String, List<String>> keywords) {
		this.language = language;
		this.keywords = keywords;
	}

	public List<String> getFeatureKeywords() {
		return keywords.get("feature");
	}

	public List<String> getScenarioKeywords() {
		return keywords.get("scenario");
	}

	public List<String> getBackgroundKeywords() {
		return keywords.get("background");
	}

	public List<String> getScenarioOutlineKeywords() {
		return keywords.get("scenarioOutline");
	}

	public List<String> getExamplesKeywords() {
		return keywords.get("examples");
	}

	public List<String> getStepKeywords() {
		HashSet<String> keywords = new HashSet<>();
		keywords.addAll(getGivenKeywords());
		keywords.addAll(getWhenKeywords());
		keywords.addAll(getThenKeywords());
		keywords.addAll(getAndKeywords());
		keywords.addAll(getButKeywords());
		return new ArrayList<>(keywords);
	}

	public List<String> getGivenKeywords() {
		return keywords.get("given");
	}

	public List<String> getWhenKeywords() {
		return keywords.get("when");
	}

	public List<String> getThenKeywords() {
		return keywords.get("then");
	}

	public List<String> getAndKeywords() {
		return keywords.get("and");
	}

	public List<String> getButKeywords() {
		return keywords.get("but");
	}

	public String getLanguage() {
		return language;
	}
}
