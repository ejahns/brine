//	  This code was generated by Berp (http://https://github.com/gasparnagy/berp/).
//
//	  Changes to this file may cause incorrect behavior and will be lost if
//	  the code is regenerated.
package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Queue;

import com.github.ejahns.model.GherkinDocument;
import com.github.ejahns.model.Feature;
import com.github.ejahns.model.Background;
import com.github.ejahns.model.ScenarioDefinition;
import com.github.ejahns.model.Scenario;
import com.github.ejahns.model.ScenarioOutline;
import com.github.ejahns.model.ExamplesDefinition;
import com.github.ejahns.model.Examples;
import com.github.ejahns.model.ExamplesTable;
import com.github.ejahns.model.Step;
import com.github.ejahns.model.DataTable;
import com.github.ejahns.model.DocString;

import static com.github.ejahns.Parser.TokenType.*;

public class Parser {

	private static final ThreadLocal<GherkinElementStack> STACK = new ThreadLocal<>();
    private static final ThreadLocal<TokenQueue> QUEUE = new ThreadLocal<>();

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
		OtherToken,
	}

	public GherkinDocument parse(TokenQueue queue) {
		QUEUE.set(queue);
        STACK.set(new GherkinElementStack());
		STACK.get().push(GherkinDocument.class);
		int state = 0;
		Token token;
		do {
			token = queue.next();
			state = matchToken(state, token);
		} while (!token.isEOF());

		STACK.get().collapse(GherkinDocument.class);

		return STACK.get().resolve();
	}

	private int matchToken(int state, Token token) {
		int newState;
		switch(state) {
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
			case 17:
				newState = matchTokenAt_17(token);
				break;
			case 18:
				newState = matchTokenAt_18(token);
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
			default:
				throw new IllegalStateException("Unknown state: " + state);
		}
		return newState;
	}

	// Start
	private int matchTokenAt_0(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(LanguageToken)) {
			STACK.get().push(Feature.class);
			STACK.get().consume(token);
			return 1;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().push(Feature.class);
			STACK.get().consume(token);
			return 1;
		}
		if (token.getType().equals(FeatureLineToken)) {
			STACK.get().push(Feature.class);
			STACK.get().consume(token);
			return 2;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 0;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 0;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:0>#Language:0
	private int matchTokenAt_1(Token token) {
		if (token.getType().equals(TagLineToken)) {
			STACK.get().consume(token);
			return 1;
		}
		if (token.getType().equals(FeatureLineToken)) {
			STACK.get().consume(token);
			return 2;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 1;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 1;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:2>#FeatureLine:0
	private int matchTokenAt_2(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(BackgroundLineToken)) {
			STACK.get().push(Background.class);
			STACK.get().consume(token);
			return 3;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 2;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:4>Background:0>#BackgroundLine:0
	private int matchTokenAt_3(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Background.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 3;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:4>Background:2>Step:0>#StepLine:0
	private int matchTokenAt_4(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().push(DataTable.class);
			STACK.get().consume(token);
			return 5;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().push(DocString.class);
			STACK.get().consume(token);
			return 21;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 4;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 4;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:4>Background:2>Step:1>__alt1:0>DataTable:0>#TableRow:0
	private int matchTokenAt_5(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().consume(token);
			return 5;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 5;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 5;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:0>#TagLine:0
	private int matchTokenAt_6(Token token) {
		if (token.getType().equals(TagLineToken)) {
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 6;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:0>Scenario:0>#ScenarioLine:0
	private int matchTokenAt_7(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 7;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:0>Scenario:2>Step:0>#StepLine:0
	private int matchTokenAt_8(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().push(DataTable.class);
			STACK.get().consume(token);
			return 9;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().push(DocString.class);
			STACK.get().consume(token);
			return 19;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 8;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 8;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:0>Scenario:2>Step:1>__alt1:0>DataTable:0>#TableRow:0
	private int matchTokenAt_9(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().consume(token);
			return 9;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 9;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 9;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:0>#ScenarioOutlineLine:0
	private int matchTokenAt_10(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 11;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 10;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:2>Step:0>#StepLine:0
	private int matchTokenAt_11(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().push(DataTable.class);
			STACK.get().consume(token);
			return 12;
		}
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().push(DocString.class);
			STACK.get().consume(token);
			return 17;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 11;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 11;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 11;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:2>Step:1>__alt1:0>DataTable:0>#TableRow:0
	private int matchTokenAt_12(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().consume(token);
			return 12;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 11;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DataTable.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 12;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 12;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:3>ExamplesDefinition:0>#TagLine:0
	private int matchTokenAt_13(Token token) {
		if (token.getType().equals(TagLineToken)) {
			STACK.get().consume(token);
			return 13;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 13;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 13;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:3>ExamplesDefinition:1>Examples:0>#ExamplesLine:0
	private int matchTokenAt_14(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().push(ExamplesTable.class);
			STACK.get().consume(token);
			return 15;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 14;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:3>ExamplesDefinition:1>Examples:2>ExamplesTable:0>#TableRow:0
	private int matchTokenAt_15(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(TableRowToken)) {
			STACK.get().consume(token);
			return 15;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(ExamplesTable.class);
			STACK.get().collapse(Examples.class);
			STACK.get().collapse(ExamplesDefinition.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 15;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 15;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:2>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_17(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().consume(token);
			return 18;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 17;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:1>ScenarioOutline:2>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_18(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 11;
		}
		if (token.getType().equals(TagLineToken)) {
			if (lookahead_0(token))
			{
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().consume(token);
			return 13;
			}
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ExamplesLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(ExamplesDefinition.class);
			STACK.get().push(Examples.class);
			STACK.get().consume(token);
			return 14;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(ScenarioOutline.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 18;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 18;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:0>Scenario:2>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_19(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().consume(token);
			return 20;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 19;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:5>ScenarioDefinition:1>__alt0:0>Scenario:2>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_20(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 8;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Scenario.class);
			STACK.get().collapse(ScenarioDefinition.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 20;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 20;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:4>Background:2>Step:1>__alt1:1>DocString:0>#DocStringSeparator:0
	private int matchTokenAt_21(Token token) {
		if (token.getType().equals(DocStringSeparatorToken)) {
			STACK.get().consume(token);
			return 22;
		}
		if (token.getType().equals(OtherToken)) {
			STACK.get().consume(token);
			return 21;
		}
		throw new IllegalStateException();
	}

	// GherkinDocument:0>Feature:4>Background:2>Step:1>__alt1:1>DocString:2>#DocStringSeparator:0
	private int matchTokenAt_22(Token token) {
		if (token.getType().equals(EOFToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().collapse(Feature.class);
			STACK.get().consume(token);
			return 16;
		}
		if (token.getType().equals(StepLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().push(Step.class);
			STACK.get().consume(token);
			return 4;
		}
		if (token.getType().equals(TagLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().consume(token);
			return 6;
		}
		if (token.getType().equals(ScenarioLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(Scenario.class);
			STACK.get().consume(token);
			return 7;
		}
		if (token.getType().equals(ScenarioOutlineLineToken)) {
			STACK.get().collapse(DocString.class);
			STACK.get().collapse(Step.class);
			STACK.get().collapse(Background.class);
			STACK.get().push(ScenarioDefinition.class);
			STACK.get().push(ScenarioOutline.class);
			STACK.get().consume(token);
			return 10;
		}
		if (token.getType().equals(CommentToken)) {
			STACK.get().consume(token);
			return 22;
		}
		if (token.getType().equals(EmptyToken)) {
			STACK.get().consume(token);
			return 22;
		}
		throw new IllegalStateException();
	}

	private boolean lookahead_0(Token currentToken) {
		Token token;
		Queue<Token> newQueue = new ArrayDeque<Token>();
		boolean match = false;
		do
		{
			token = QUEUE.get().next();
			newQueue.add(token);

			if (false
				|| token.getType().equals(ExamplesLineToken)
			)
			{
				match = true;
				break;
			}
		} while (false
			|| token.getType().equals(EmptyToken)
			|| token.getType().equals(CommentToken)
			|| token.getType().equals(TagLineToken)
		);
		QUEUE.get().add(newQueue);
		return match;
	}
}
