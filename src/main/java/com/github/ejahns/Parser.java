//	  This code was generated by Berp (http://https://github.com/gasparnagy/berp/).
//
//	  Changes to this file may cause incorrect behavior and will be lost if
//	  the code is regenerated.
package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.github.ejahns.ParserException.UnexpectedTokenException;
import com.github.ejahns.model.Background;
import com.github.ejahns.model.DataTable;
import com.github.ejahns.model.DocString;
import com.github.ejahns.model.Examples;
import com.github.ejahns.model.ExamplesTable;
import com.github.ejahns.model.Feature;
import com.github.ejahns.model.Scenario;
import com.github.ejahns.model.ScenarioOutline;
import com.github.ejahns.model.Step;
import com.github.ejahns.model.TableRow;
import com.github.ejahns.token.Token;
import com.github.ejahns.token.TokenProvider;

import static com.github.ejahns.Parser.TokenType.*;
import static java.util.Arrays.*;

public class Parser {

	private final GherkinElementStackHandler stackHandler = new GherkinElementStackHandler();
	private TokenProvider queue;
	private boolean collectErrors = false;
	private List<String> errors;

	public Feature parse(TokenProvider queue) {
		this.queue = queue;
		stackHandler.push(Feature.class);
		int state = 0;
		Token token;
		do {
			token = queue.next();
			state = matchToken(state, token);
		} while (!token.isEOF());

		stackHandler.collapse(Feature.class);

		return stackHandler.resolve();
	}

	public Feature parse(TokenProvider queue, List<String> errors) {
		this.collectErrors = true;
		this.errors = errors;
		this.stackHandler.setCollectErrors(errors);
		return parse(queue);
	}

	private int matchToken(int state, Token token) {
		int newState;
		switch (state) {
			case 0:
				newState = matchTokenAt_0(token);
				break;
			case 1:
				newState = matchTokenAt_1(token);
				break;
			case 2:
				newState = matchTokenAt_2(token);
				break;
			case 3:
				newState = matchTokenAt_3(token);
				break;
			case 4:
				newState = matchTokenAt_4(token);
				break;
			case 5:
				newState = matchTokenAt_5(token);
				break;
			case 6:
				newState = matchTokenAt_6(token);
				break;
			case 7:
				newState = matchTokenAt_7(token);
				break;
			case 8:
				newState = matchTokenAt_8(token);
				break;
			case 9:
				newState = matchTokenAt_9(token);
				break;
			case 10:
				newState = matchTokenAt_10(token);
				break;
			case 11:
				newState = matchTokenAt_11(token);
				break;
			case 12:
				newState = matchTokenAt_12(token);
				break;
			case 13:
				newState = matchTokenAt_13(token);
				break;
			case 14:
				newState = matchTokenAt_14(token);
				break;
			case 15:
				newState = matchTokenAt_15(token);
				break;
			case 16:
				newState = matchTokenAt_16(token);
				break;
			case 17:
				newState = matchTokenAt_17(token);
				break;
			case 19:
				newState = matchTokenAt_19(token);
				break;
			case 20:
				newState = matchTokenAt_20(token);
				break;
			case 21:
				newState = matchTokenAt_21(token);
				break;
			case 22:
				newState = matchTokenAt_22(token);
				break;
			case 23:
				newState = matchTokenAt_23(token);
				break;
			case 24:
				newState = matchTokenAt_24(token);
				break;
			default:
				throw new IllegalStateException("Unknown state: " + state);
		}
		return newState;
	}

	// Start
	private int matchTokenAt_0(Token token) {
		if (token.getType().equals(LanguageToken)) {
			stackHandler.consume(token);
			return 1;
		}
		if (token.getType().equals(TagLineToken)) {
			stackHandler.consume(token);
			return 1;
		}
		if (token.getType().equals(FeatureLineToken)) {
			stackHandler.consume(token);
			return 2;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 0;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 0;
		}

		List<String> expectedTokens = asList("Language", "TagLine", "FeatureLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 0;
	}

	// Feature:0>#Language:0
	private int matchTokenAt_1(Token token) {
		if (token.getType().equals(TagLineToken)) {
			stackHandler.consume(token);
			return 1;
		}
		if (token.getType().equals(FeatureLineToken)) {
			stackHandler.consume(token);
			return 2;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 1;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 1;
		}

		List<String> expectedTokens = asList("TagLine", "FeatureLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 1;
	}

	// Feature:2>#FeatureLine:0
	private int matchTokenAt_2(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(BackgroundLineToken)) {
			stackHandler.push(Background.class);
			stackHandler.consume(token);
			return 3;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 2;
		}

		List<String> expectedTokens = asList("EOF", "BackgroundLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 2;
	}

	// Feature:4>Background:0>#BackgroundLine:0
	private int matchTokenAt_3(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(Background.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(Background.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(Background.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(Background.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(Background.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 3;
		}

		List<String> expectedTokens = asList("EOF", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 3;
	}

	// Feature:4>Background:2>Step:0>#StepLine:0
	private int matchTokenAt_4(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(TableRowToken)) {
			stackHandler.push(DataTable.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 5;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.push(DocString.class);
			stackHandler.consume(token);
			return 23;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 4;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 4;
		}

		List<String> expectedTokens = asList("EOF", "TableRow", "DocStringSeparator", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 4;
	}

	// Feature:4>Background:2>Step:1>__alt1:0>DataTable:0>TableRow:0>#TableRow:0
	private int matchTokenAt_5(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(TableRowToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 5;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(DataTable.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(DataTable.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 5;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 5;
		}

		List<String> expectedTokens = asList("EOF", "TableRow", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 5;
	}

	// Feature:5>__alt0:0>Scenario:0>#TagLine:0
	private int matchTokenAt_6(Token token) {
		if (token.getType().equals(TagLineToken)) {
			stackHandler.consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 6;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 6;
		}

		List<String> expectedTokens = asList("TagLine", "ScenarioLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 6;
	}

	// Feature:5>__alt0:0>Scenario:1>#ScenarioLine:0
	private int matchTokenAt_7(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(Scenario.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(Scenario.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(Scenario.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(Scenario.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(Scenario.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 7;
		}

		List<String> expectedTokens = asList("EOF", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 7;
	}

	// Feature:5>__alt0:0>Scenario:3>Step:0>#StepLine:0
	private int matchTokenAt_8(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(TableRowToken)) {
			stackHandler.push(DataTable.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 9;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.push(DocString.class);
			stackHandler.consume(token);
			return 21;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 8;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 8;
		}

		List<String> expectedTokens = asList("EOF", "TableRow", "DocStringSeparator", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 8;
	}

	// Feature:5>__alt0:0>Scenario:3>Step:1>__alt1:0>DataTable:0>TableRow:0>#TableRow:0
	private int matchTokenAt_9(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(TableRowToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 9;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(DataTable.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(DataTable.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 9;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 9;
		}

		List<String> expectedTokens = asList("EOF", "TableRow", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 9;
	}

	// Feature:5>__alt0:1>ScenarioOutline:0>#TagLine:0
	private int matchTokenAt_10(Token token) {
		if (token.getType().equals(TagLineToken)) {
			stackHandler.consume(token);
			return 10;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 10;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 10;
		}

		List<String> expectedTokens = asList("TagLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 10;
	}

	// Feature:5>__alt0:1>ScenarioOutline:1>#ScenarioOutlineLine:0
	private int matchTokenAt_11(Token token) {
		if (token.getType().equals(StepLineToken)) {
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 12;
		}
		if (token.getType().equals(TagLineToken)) {
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 11;
		}

		List<String> expectedTokens = asList("StepLine", "TagLine", "ExamplesLine", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 11;
	}

	// Feature:5>__alt0:1>ScenarioOutline:3>Step:0>#StepLine:0
	private int matchTokenAt_12(Token token) {
		if (token.getType().equals(TableRowToken)) {
			stackHandler.push(DataTable.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 13;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.push(DocString.class);
			stackHandler.consume(token);
			return 19;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 12;
		}
		if (token.getType().equals(TagLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 12;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 12;
		}

		List<String> expectedTokens = asList("TableRow", "DocStringSeparator", "StepLine", "TagLine", "ExamplesLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 12;
	}

	// Feature:5>__alt0:1>ScenarioOutline:3>Step:1>__alt1:0>DataTable:0>TableRow:0>#TableRow:0
	private int matchTokenAt_13(Token token) {
		if (token.getType().equals(TableRowToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 13;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 12;
		}
		if (token.getType().equals(TagLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(DataTable.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 13;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 13;
		}

		List<String> expectedTokens = asList("TableRow", "StepLine", "TagLine", "ExamplesLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 13;
	}

	// Feature:5>__alt0:1>ScenarioOutline:4>Examples:0>#TagLine:0
	private int matchTokenAt_14(Token token) {
		if (token.getType().equals(TagLineToken)) {
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 14;
		}

		List<String> expectedTokens = asList("TagLine", "ExamplesLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 14;
	}

	// Feature:5>__alt0:1>ScenarioOutline:4>Examples:1>#ExamplesLine:0
	private int matchTokenAt_15(Token token) {
		if (token.getType().equals(TableRowToken)) {
			stackHandler.push(ExamplesTable.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 16;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 15;
		}

		List<String> expectedTokens = asList("TableRow", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 15;
	}

	// Feature:5>__alt0:1>ScenarioOutline:4>Examples:3>ExamplesTable:0>TableRow:0>#TableRow:0
	private int matchTokenAt_16(Token token) {
		if (token.getType().equals(TableRowToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 17;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 16;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 16;
		}

		List<String> expectedTokens = asList("TableRow", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 16;
	}

	// Feature:5>__alt0:1>ScenarioOutline:4>Examples:3>ExamplesTable:1>TableRow:0>#TableRow:0
	private int matchTokenAt_17(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(ExamplesTable.class);
			stackHandler.collapse(Examples.class);
			stackHandler.collapse(ScenarioOutline.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(TableRowToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.push(TableRow.class);
			stackHandler.consume(token);
			return 17;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_2()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(ExamplesTable.class);
				stackHandler.collapse(Examples.class);
				stackHandler.push(Examples.class);
				stackHandler.consume(token);
				return 14;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(ExamplesTable.class);
				stackHandler.collapse(Examples.class);
				stackHandler.collapse(ScenarioOutline.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(TableRow.class);
				stackHandler.collapse(ExamplesTable.class);
				stackHandler.collapse(Examples.class);
				stackHandler.collapse(ScenarioOutline.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(ExamplesTable.class);
			stackHandler.collapse(Examples.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(ExamplesTable.class);
			stackHandler.collapse(Examples.class);
			stackHandler.collapse(ScenarioOutline.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(TableRow.class);
			stackHandler.collapse(ExamplesTable.class);
			stackHandler.collapse(Examples.class);
			stackHandler.collapse(ScenarioOutline.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 17;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 17;
		}

		List<String> expectedTokens = asList("EOF", "TableRow", "TagLine", "ExamplesLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 17;
	}

	// Feature:5>__alt0:1>ScenarioOutline:3>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_19(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.consume(token);
			return 20;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 19;
		}

		List<String> expectedTokens = asList("DocStringSeparator", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 19;
	}

	// Feature:5>__alt0:1>ScenarioOutline:3>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_20(Token token) {
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 12;
		}
		if (token.getType().equals(TagLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 14;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Examples.class);
			stackHandler.consume(token);
			return 15;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 20;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 20;
		}

		List<String> expectedTokens = asList("StepLine", "TagLine", "ExamplesLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 20;
	}

	// Feature:5>__alt0:0>Scenario:3>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_21(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.consume(token);
			return 22;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 21;
		}

		List<String> expectedTokens = asList("DocStringSeparator", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 21;
	}

	// Feature:5>__alt0:0>Scenario:3>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_22(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(DocString.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(DocString.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Scenario.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Scenario.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 22;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 22;
		}

		List<String> expectedTokens = asList("EOF", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 22;
	}

	// Feature:4>Background:2>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_23(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			stackHandler.consume(token);
			return 24;
		}
		if (token.getType().equals(OtherToken)) {
			stackHandler.consume(token);
			return 23;
		}

		List<String> expectedTokens = asList("DocStringSeparator", "Other");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 23;
	}

	// Feature:4>Background:2>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_24(Token token) {
		if (token.getType().equals(EOFToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.consume(token);
			return 18;
		}
		if (token.getType().equals(StepLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.push(Step.class);
			stackHandler.consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0()) {
				stackHandler.collapse(DocString.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(Scenario.class);
				stackHandler.consume(token);
				return 6;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_1()) {
				stackHandler.collapse(DocString.class);
				stackHandler.collapse(Step.class);
				stackHandler.collapse(Background.class);
				stackHandler.push(ScenarioOutline.class);
				stackHandler.consume(token);
				return 10;
			}
		}
		if (token.getType().equals(ScenarioLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(Scenario.class);
			stackHandler.consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			stackHandler.collapse(DocString.class);
			stackHandler.collapse(Step.class);
			stackHandler.collapse(Background.class);
			stackHandler.push(ScenarioOutline.class);
			stackHandler.consume(token);
			return 11;
		}
		if (token.getType().equals(CommentToken)) {
			stackHandler.consume(token);
			return 24;
		}
		if (token.getType().equals(EmptyToken)) {
			stackHandler.consume(token);
			return 24;
		}

		List<String> expectedTokens = asList("EOF", "StepLine", "TagLine", "ScenarioLine", "ScenarioOutlineLine", "Comment", "Empty");
		UnexpectedTokenException error = new UnexpectedTokenException(token.toString(), expectedTokens, token.getLineNum());
		if (!collectErrors) {
			throw error;
		}
		errors.add(error.toString());
		return 24;
	}

	private boolean lookahead_0() {
		Token token;
		Deque<Token> newQueue = new ArrayDeque<>();
		boolean match = false;
		do {
			token = queue.next();
			newQueue.add(token);
			if (asList(ScenarioLineToken).contains(token.getType())) {
				match = true;
				break;
			}

			{
				if (false
					|| token.getType().equals(ScenarioLineToken)
					) {
					match = true;
					break;
				}
			}
		} while (false
			|| token.getType().equals(EmptyToken)
			|| token.getType().equals(CommentToken)
			|| token.getType().equals(TagLineToken)
			);
		queue.addToFront(newQueue);
		return match;
	}

	private boolean lookahead_1() {
		Token token;
		Deque<Token> newQueue = new ArrayDeque<>();
		boolean match = false;
		do {
			token = queue.next();
			newQueue.add(token);

			if (false
				|| token.getType().equals(ScenarioOutlineLineToken)
				) {
				match = true;
				break;
			}
		} while (false
			|| token.getType().equals(EmptyToken)
			|| token.getType().equals(CommentToken)
			|| token.getType().equals(TagLineToken)
			);
		queue.addToFront(newQueue);
		return match;
	}

	private boolean lookahead_2() {
		Token token;
		Deque<Token> newQueue = new ArrayDeque<>();
		boolean match = false;
		do {
			token = queue.next();
			newQueue.add(token);

			if (false
				|| token.getType().equals(ExamplesLineToken)
				) {
				match = true;
				break;
			}
		} while (false
			|| token.getType().equals(EmptyToken)
			|| token.getType().equals(CommentToken)
			|| token.getType().equals(TagLineToken)
			);
		queue.addToFront(newQueue);
		return match;
	}

	public enum TokenType {
		EOFToken,
		EmptyToken,
		CommentToken,
		TagLineToken,
		FeatureLineToken,
		BackgroundLineToken,
		ScenarioLineToken,
		ScenarioOutlineLineToken,
		ExamplesLineToken,
		StepLineToken,
		DocStringSeparatorToken,
		TableRowToken,
		LanguageToken,
		OtherToken,;

		@Override
		public String toString() {
			return name().replace("Token", "");
		}
	}
}
