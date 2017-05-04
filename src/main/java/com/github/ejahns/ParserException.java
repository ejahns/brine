package com.github.ejahns;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ParserException extends RuntimeException {

	ParserException(String message) {
		super(message);
	}

	public static class UnexpectedTokenException extends ParserException {
		private String token;
		private List<String> expectedTypes;
		private int lineNum;

		public UnexpectedTokenException(String token, List<String> expectedTypes, int lineNum) {
			super(getMessage(token, expectedTypes, lineNum));
			this.token = token;
			this.expectedTypes = expectedTypes;
			this.lineNum = lineNum;
		}

		private static String getMessage(String token, List<String> expectedTypes, int lineNum) {
			return
				String.format("Expected one of %s but got %s at line %s",
					StringUtils.join(expectedTypes, ", "),
					token,
					lineNum
				);
		}
	}
}
