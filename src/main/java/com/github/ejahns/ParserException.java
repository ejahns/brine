package com.github.ejahns;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.ejahns.model.TableRow;

class ParserException extends RuntimeException {

	ParserException(String message) {
		super(message);
	}

	public static class UnexpectedTableRowException extends ParserException {

		private final TableRow row;
		private final int expectedCount;
		private final int actualCount;
		private final int lineNum;

		public UnexpectedTableRowException(TableRow row, int expectedCount, int actualCount, int lineNum) {
			super(getMessage(row, expectedCount, actualCount, lineNum));
			this.row = row;
			this.expectedCount = expectedCount;
			this.actualCount = actualCount;
			this.lineNum = lineNum;
		}

		private static String getMessage(TableRow row, int expectedCount, int actualCount, int lineNum) {
			return
				String.format("Expected row with %s cells but got %s cells at line %s: %s",
					expectedCount,
					actualCount,
					lineNum,
					row
				);
		}
	}

	public static class UnexpectedTokenException extends ParserException {

		private final String token;
		private final List<String> expectedTypes;
		private final int lineNum;

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
