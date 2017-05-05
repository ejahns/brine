package com.github.ejahns;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ParserException extends RuntimeException {

	ParserException(String message) {
		super(message);
	}

	public static class UnexpectedTableRowException extends ParserException {

		private String token;
		private int expectedCount;
		private int actualCount;
		private int lineNum;

		public UnexpectedTableRowException(String token, int expectedCount, int actualCount, int lineNum) {
			super(getMessage(token, expectedCount, actualCount, lineNum));
			this.token = token;
			this.expectedCount = expectedCount;
			this.actualCount = actualCount;
			this.lineNum = lineNum;
		}

		private static String getMessage(String token, int expectedCount, int actualCount, int lineNum) {
			return
				String.format("Expected row with %s cells but got %s cells at line %s: %s",
					expectedCount,
					actualCount,
					lineNum,
					token
				);
		}
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
