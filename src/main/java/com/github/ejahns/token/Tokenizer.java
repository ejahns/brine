package com.github.ejahns.token;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ejahns.KeywordProvider;
import com.github.ejahns.LanguageProvider;
import com.github.ejahns.Parser;

import static com.github.ejahns.Constants.*;
import static com.github.ejahns.Parser.TokenType.*;

public class Tokenizer {

	private static final Pattern LANGUAGE_PATTERN = Pattern.compile("^#\\s*language\\s*:\\s*([a-zA-Z\\-_]+)$");

	private KeywordProvider keywordProvider;
	private boolean readingDocString = false;
	private String currentDocStringSeparator = DOCSTRING_SEPARATOR;

	public Tokenizer() {
		keywordProvider = LanguageProvider.getKeywordProvider();
	}

	public Token tokenize(String line, int lineNum) throws TokenizerException {
		if (null == line) {
			return new Token(null, EOFToken, lineNum);
		}
		String trim = line.trim();
		if (readingDocString) {
			if (trim.startsWith(currentDocStringSeparator)) {
				readingDocString = false;
				return new Token(null, DocStringSeparatorToken, lineNum);
			}
			else {
				return new Token(trim, OtherToken, lineNum);
			}
		}
		if (trim.startsWith(DOCSTRING_SEPARATOR)) {
			currentDocStringSeparator = DOCSTRING_SEPARATOR;
			readingDocString = true;
			return new Token(null, DocStringSeparatorToken, lineNum);
		}
		if (trim.startsWith(ALTERNATE_DOCSTRING_SEPARATOR)) {
			currentDocStringSeparator = ALTERNATE_DOCSTRING_SEPARATOR;
			readingDocString = true;
			return new Token(null, DocStringSeparatorToken, lineNum);
		}
		if (trim.equals("")) {
			return null;
		}
		Matcher matcher = LANGUAGE_PATTERN.matcher(trim);
		if (matcher.matches()) {
			String language = matcher.group(1);
			keywordProvider = LanguageProvider.getKeywordProvider(language);
			return new Token(language, LanguageToken, lineNum);
		}
		if (trim.startsWith(COMMENT_PREFIX)) {
			return null;
		}
		if (trim.startsWith(TAG_PREFIX)) {
			return new Token(trim, TagLineToken, lineNum);
		}
		if (trim.startsWith(CELL_SEPARATOR)) {
			if (!trim.endsWith(CELL_SEPARATOR)) {
				throw new TokenizerException(
					String.format(
						"expected final '|' at end of line %s: %s",
						lineNum,
						trim)
				);
			}
			return new Token(trim, TableRowToken, lineNum);
		}
		Token match;
		match = match(keywordProvider.getScenarioOutlineKeywords(), trim, ScenarioOutlineLineToken, lineNum);
		if (null != match) {
			return match;
		}
		match = match(keywordProvider.getFeatureKeywords(), trim, FeatureLineToken, lineNum);
		if (null != match) {
			return match;
		}
		match = match(keywordProvider.getScenarioKeywords(), trim, ScenarioLineToken, lineNum);
		if (null != match) {
			return match;
		}
		match = match(keywordProvider.getBackgroundKeywords(), trim, BackgroundLineToken, lineNum);
		if (null != match) {
			return match;
		}
		match = match(keywordProvider.getExamplesKeywords(), trim, ExamplesLineToken, lineNum);
		if (null != match) {
			return match;
		}
		match = match(keywordProvider.getStepKeywords(), trim, StepLineToken, lineNum);
		if (null != match) {
			return match;
		}
		return new Token(trim, OtherToken, lineNum);
	}

	private Token match(List<String> keywords, String line, Parser.TokenType type, int lineNum) {
		for (String keyword : keywords) {
			if (line.startsWith(keyword)) {
				String trim = line.substring(keyword.length()).trim();
				if (trim.startsWith(TITLE_SEPARATOR)) {
					trim = trim.substring(1).trim();
				}
				trim = trim.equals("") ? null : trim;
				return new Token(keyword.trim(), trim, type, lineNum);
			}
		}
		return null;
	}
}
