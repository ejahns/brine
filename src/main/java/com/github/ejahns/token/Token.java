package com.github.ejahns.token;

import com.github.ejahns.Parser.TokenType;

public class Token {

	private final TokenType type;
	private final String keyword;
	private final String line;
	private final int lineNum;

	public Token(String keyword, String line, TokenType type, int lineNum) {
		this.line = line;
		this.type = type;
		this.keyword = keyword;
		this.lineNum = lineNum;
	}

	public Token(String line, TokenType type, int lineNum) {
		this.line = line;
		this.type = type;
		this.keyword = null;
		this.lineNum = lineNum;
	}

	public String getLine() {
		return line;
	}

	public int getLineNum() {
		return lineNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public TokenType getType() {
		return this.type;
	}

	public boolean isEOF() {
		return type.equals(TokenType.EOFToken);
	}

	@Override
	public String toString() {
		return String.format("%s: '%s'", type, line);
	}
}
