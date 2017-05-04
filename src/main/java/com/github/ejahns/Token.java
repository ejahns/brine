package com.github.ejahns;

import com.github.ejahns.Parser.TokenType;

public class Token {

	private TokenType type;
	private String keyword;
	private String line;
	private int lineNum;

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
		return null == line;
	}

	@Override
	public String toString() {
		return type + ": " + line;
	}
}
