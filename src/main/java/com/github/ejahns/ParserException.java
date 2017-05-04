package com.github.ejahns;

import java.util.List;

import com.github.ejahns.Parser.TokenType;
import com.sun.deploy.util.StringUtils;

public class ParserException extends RuntimeException {

	ParserException(String message) {
		super(message);
	}

	public static class UnexpectedTokenException extends ParserException {
		private TokenType token;
		private List<TokenType> expectedTypes;
		private int lineNum;

		public UnexpectedTokenException(TokenType token, List<TokenType> expectedTypes, int lineNum) {
			super(getMessage(token, expectedTypes, lineNum));
			this.token = token;
			this.expectedTypes = expectedTypes;
			this.lineNum = lineNum;
		}

		private static String getMessage(TokenType token, List<TokenType> expectedTypes, int lineNum) {
			return
				String.format("Expected one of %s but got %s at line %s",
					StringUtils.join(expectedTypes, ", "),
					token,
					lineNum
				);
		}
	}
}
