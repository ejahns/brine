package com.github.ejahns;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import static com.github.ejahns.Parser.TokenType.*;

class TokenQueue {

	private static final Map<String, Parser.TokenType> lineKeys =
		ImmutableMap.of(
			"Given ", StepLineToken,
			"When ", StepLineToken,
			"Then ", StepLineToken,
			"And ", StepLineToken,
			"* ", StepLineToken
		);
	private static final Map<String, Parser.TokenType> stepKeys =
		ImmutableMap.of(
			"Feature:", FeatureLineToken,
			"Scenario:", ScenarioLineToken,
			"Scenario Outline:", ScenarioOutlineLineToken,
			"Background:", BackgroundLineToken,
			"Examples:", ExamplesLineToken
		);

	private List<String> errors;
	private boolean collectErrors = false;
	private final Deque<Token> tokens = new ArrayDeque<>();

	private boolean inDocString = false;

	public TokenQueue() {

	}

	public TokenQueue(Reader reader) {
		readToQueue(reader);
	}

	public TokenQueue(Reader reader, List<String> errors) {
		this.collectErrors = true;
		this.errors = errors;
		readToQueue(reader);
	}

	public Token next() {
		return tokens.pop();
	}

	public void add(Deque<Token> queue) {
		tokens.addAll(queue);
	}

	public void addToFront(Deque<Token> queue) {
		Iterator<Token> tokenIterator = queue.descendingIterator();
		while (tokenIterator.hasNext()) {
			tokens.addFirst(tokenIterator.next());
		}
	}

	private void readToQueue(Reader reader) {
		LineNumberReader lineNumberReader = new LineNumberReader(reader);
		String line;
		do {
			try {
				line = lineNumberReader.readLine();
				if (null != line) {
					line = line.replaceAll("\\p{C}", "");
				}
				line = null == line ? null : line.replaceAll("\\p{C}", "");
				Token token = tokenize(line, lineNumberReader.getLineNumber());
				if (null != token) {
					tokens.add(token);
				}
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		} while (null != line);
	}

	//TODO fix rules for DocStringSeparator...Gherkin allows an alternate separator ''', and any characters included in a DocString should be kept
	//TODO handle LanguageToken?
	private Token tokenize(String s, int i) {
		if (null == s) {
			return new Token(null, EOFToken, i);
		}
		String trimmed = s.trim();
		if (trimmed.startsWith("\"\"\"")) {
			inDocString = !inDocString;
			return new Token(null, DocStringSeparatorToken, i);
		}
		if (inDocString){
			return new Token(s, OtherToken, i);
		}
		if (trimmed.startsWith("#") || trimmed.equals("")) {
			return null;
		}
		if (trimmed.startsWith("@")) {
			return new Token(trimmed, TagLineToken, i);
		}
		if (trimmed.startsWith("|")) {
			if (!trimmed.endsWith("|")) {
				TokenizerException error = new TokenizerException(
					String.format(
						"expected final '|' at end of line %s: %s",
						i,
						trimmed)
				);
				if (!collectErrors) {
					throw error;
				}
				errors.add(error.toString());
				return null;
			}
			return new Token(trimmed, TableRowToken, i);
		}
		for (String key : lineKeys.keySet()) {
			if (trimmed.startsWith(key)) {
				trimmed = trimmed.replaceFirst(key, "").trim();
				trimmed = trimmed.equals("") ? null : trimmed;
				return new Token(key.trim(), trimmed, lineKeys.get(key), i);
			}
		}
		for (String key : stepKeys.keySet()) {
			if (trimmed.startsWith(key)) {
				trimmed = trimmed.replaceFirst(key, "").trim();
				trimmed = trimmed.equals("") ? null : trimmed;
				return new Token(key, trimmed, stepKeys.get(key), i);
			}
		}
		return new Token("", trimmed, OtherToken, i);
	}
}
