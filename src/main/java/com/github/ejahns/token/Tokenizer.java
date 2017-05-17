package com.github.ejahns.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.ejahns.Constants.*;
import static com.github.ejahns.Parser.TokenType.*;

class Tokenizer {

	private static final Pattern LANGUAGE_PATTERN = Pattern.compile("^#\\s*language\\s*:\\s*([a-zA-Z\\-_]+)$");

	private KeywordProvider keywordProvider;
	private boolean readingDocString = false;
	private String currentDocStringSeparator = DOCSTRING_SEPARATOR;

	Tokenizer() {
		keywordProvider = LanguageProvider.getKeywordProvider();
	}

	Token tokenize(String line, int lineNum) throws TokenizerException {
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
		if (trim.startsWith(DOCSTRING_SEPARATOR)) {
			currentDocStringSeparator = DOCSTRING_SEPARATOR;
			readingDocString = true;
			String type = trim.substring(DOCSTRING_SEPARATOR.length());
			type = type.equals("") ? null : type;
			return new Token(type, DocStringSeparatorToken, lineNum);
		}
		if (trim.startsWith(ALTERNATE_DOCSTRING_SEPARATOR)) {
			currentDocStringSeparator = ALTERNATE_DOCSTRING_SEPARATOR;
			readingDocString = true;
			String type = trim.substring(ALTERNATE_DOCSTRING_SEPARATOR.length());
			type = type.equals("") ? null : type;
			return new Token(type, DocStringSeparatorToken, lineNum);
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
		KeywordProvider.TokenAssociation tokenAssociation = keywordProvider.matchTokenType(trim);
		if (null != tokenAssociation) {
			return new Token(tokenAssociation.keyword, tokenAssociation.line, tokenAssociation.tokenType, lineNum);
		}
		return new Token(trim, OtherToken, lineNum);
	}
}
