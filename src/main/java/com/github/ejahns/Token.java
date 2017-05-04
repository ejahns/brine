package com.github.ejahns;

import com.github.ejahns.Parser.TokenType;

public class Token {

	private TokenType type;
	private String line;

	public Token(String line, TokenType type) {
		this.line = line;
		this.type = type;
	}

	public String getLine() {
		return line;
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
