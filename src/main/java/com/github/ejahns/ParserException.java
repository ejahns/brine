package com.github.ejahns;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.ejahns.model.TableRow;

public class ParserException extends RuntimeException {

	ParserException(String message) {
		super(message);
	}

	public static class UnexpectedTableRowException extends ParserException {

		private TableRow row;
		private int expectedCount;
		private int actualCount;
		private int lineNum;

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
