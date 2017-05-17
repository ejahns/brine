package com.github.ejahns.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.ejahns.Parser.TokenType;

import static com.github.ejahns.Constants.*;
import static com.github.ejahns.Parser.TokenType.*;

public class KeywordProvider {

	private final String language;
	private final Map<String, List<String>> keywords;
	private final Map<List<String>, TokenType> titleAssociations;
	private final Map<List<String>, TokenType> stepAssociations;

	KeywordProvider(String language, Map<String, List<String>> keywords) {
		this.language = language;
		this.keywords = keywords;
		titleAssociations = new LinkedHashMap<>();
		stepAssociations = new LinkedHashMap<>();
		titleAssociations.put(getFeatureKeywords(), FeatureLineToken);
		titleAssociations.put(getBackgroundKeywords(), BackgroundLineToken);
		titleAssociations.put(getScenarioKeywords(), ScenarioLineToken);
		titleAssociations.put(getScenarioOutlineKeywords(), ScenarioOutlineLineToken);
		titleAssociations.put(getExamplesKeywords(), ExamplesLineToken);
		stepAssociations.put(getStepKeywords(), StepLineToken);
	}

	TokenAssociation matchTokenType(String line) {
		for (List<String> list : titleAssociations.keySet()) {
			for (String keyword : list) {
				if (line.startsWith(keyword + TITLE_SEPARATOR)) {
					line = line.substring(keyword.length() + TITLE_SEPARATOR.length()).trim();
					line = line.equals("") ? null : line;
					return new TokenAssociation(
						keyword,
						titleAssociations.get(list),
						line
					);
				}
			}
		}
		for (List<String> list : stepAssociations.keySet()) {
			for (String keyword : list) {
				if (line.startsWith(keyword)) {
					return new TokenAssociation(
						keyword.trim(),
						stepAssociations.get(list),
						line.substring(keyword.length())
							.trim()
					);
				}
			}
		}
		return null;
	}

	private List<String> getFeatureKeywords() {
		return keywords.get("feature");
	}

	private List<String> getScenarioKeywords() {
		return keywords.get("scenario");
	}

	private List<String> getBackgroundKeywords() {
		return keywords.get("background");
	}

	private List<String> getScenarioOutlineKeywords() {
		return keywords.get("scenarioOutline");
	}

	private List<String> getExamplesKeywords() {
		return keywords.get("examples");
	}

	private List<String> getStepKeywords() {
		HashSet<String> keywords = new HashSet<>();
		keywords.addAll(getGivenKeywords());
		keywords.addAll(getWhenKeywords());
		keywords.addAll(getThenKeywords());
		keywords.addAll(getAndKeywords());
		keywords.addAll(getButKeywords());
		return new ArrayList<>(keywords);
	}

	private List<String> getGivenKeywords() {
		return keywords.get("given");
	}

	private List<String> getWhenKeywords() {
		return keywords.get("when");
	}

	private List<String> getThenKeywords() {
		return keywords.get("then");
	}

	private List<String> getAndKeywords() {
		return keywords.get("and");
	}

	private List<String> getButKeywords() {
		return keywords.get("but");
	}

	public String getLanguage() {
		return language;
	}

	class TokenAssociation {

		String keyword;
		TokenType tokenType;
		String line;

		TokenAssociation(String keyword, TokenType tokenType, String line) {
			this.keyword = keyword;
			this.tokenType = tokenType;
			this.line = line;
		}
	}
}
