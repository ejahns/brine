package com.github.ejahns.token;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class LanguageProvider {

	private static final String DEFAULT_LANGUAGE = "en";
	private static Map<String, Map<String, List<String>>> languages;

	public static KeywordProvider getKeywordProvider() {
		return getKeywordProvider(DEFAULT_LANGUAGE);
	}

	public static KeywordProvider getKeywordProvider(String language) {
		if (null == languages) {
			try {
				JsonReader jsonReader = new JsonReader(new FileReader("src/main/resources/languages.json"));
				languages = new Gson().fromJson(jsonReader, Map.class);
			}
			catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		Map<String, List<String>> keywords = languages.get(language);
		if (null == keywords) {
			throw new TokenizerException(language + " does not match any language");
		}
		return new KeywordProvider(language, keywords);
	}
}
