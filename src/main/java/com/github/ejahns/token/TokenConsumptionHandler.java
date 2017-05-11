package com.github.ejahns.token;

import java.util.ArrayList;

import com.github.ejahns.model.Background;
import com.github.ejahns.model.DocString;
import com.github.ejahns.model.Examples;
import com.github.ejahns.model.Feature;
import com.github.ejahns.model.Scenario;
import com.github.ejahns.model.ScenarioOutline;
import com.github.ejahns.model.Step;
import com.github.ejahns.model.TableRow;
import com.github.ejahns.model.interfaces.GherkinElement;
import com.github.ejahns.model.interfaces.haselement.HasTags;
import com.github.ejahns.model.interfaces.hastoken.HasDescription;

public class TokenConsumptionHandler {

	public static void consume(GherkinElement e, Token t) {
		switch (t.getType()) {
			case DocStringSeparatorToken:
				if (e instanceof DocString) {
					return;
				}
				break;
			case TagLineToken:
				if (e instanceof HasTags) {
					if (null == ((HasTags) e).getTags()) {
						((HasTags) e).setTags(new ArrayList<>());
					}
					((HasTags) e).getTags().add(t.getLine());
					return;
				}
				break;
			case StepLineToken:
				if (e instanceof Step) {
					((Step) e).setLine(t.getLineNum());
					((Step) e).setKeyword(t.getKeyword());
					((Step) e).setStep(t.getLine());
					return;
				}
				break;
			case OtherToken:
				if (e instanceof HasDescription) {
					if (null == ((HasDescription) e).getDescription()) {
						((HasDescription) e).setDescription(new ArrayList<>());
					}
					((HasDescription) e).getDescription().add(t.getLine());
					return;
				}
				if (e instanceof DocString) {
					if (null == ((DocString) e).getLines()) {
						((DocString) e).setLines(new ArrayList<>());
					}
					((DocString) e).getLines().add(t.getLine());
					return;
				}
				break;
			case TableRowToken:
				if (e instanceof TableRow) {
					((TableRow) e).setLine(t.getLineNum());
					String[] split = t.getLine().split("\\|");
					ArrayList<String> cells = new ArrayList<>();
					for (int i = 1; i < split.length; i++) {
						cells.add(split[i].trim());
					}
					((TableRow) e).setCells(cells);
					return;
				}
				break;
			case FeatureLineToken:
				if (e instanceof Feature) {
					((Feature) e).setLine(t.getLineNum());
					((Feature) e).setName(t.getLine());
					return;
				}
				break;
			case ScenarioLineToken:
				if (e instanceof Scenario) {
					((Scenario) e).setLine(t.getLineNum());
					((Scenario) e).setName(t.getLine());
					return;
				}
				break;
			case ScenarioOutlineLineToken:
				if (e instanceof ScenarioOutline) {
					((ScenarioOutline) e).setLine(t.getLineNum());
					((ScenarioOutline) e).setName(t.getLine());
					return;
				}
				break;
			case ExamplesLineToken:
				if (e instanceof Examples) {
					((Examples) e).setLine(t.getLineNum());
					((Examples) e).setName(t.getLine());
					return;
				}
				break;
			case BackgroundLineToken:
				if (e instanceof Background) {
					((Background) e).setLine(t.getLineNum());
					((Background) e).setName(t.getLine());
					return;
				}
				break;
			case EOFToken:
				if (e instanceof Feature) {
					return;
				}
				//Handle unexpected EOF
				return;
			default:
				throw new RuntimeException();
		}
		throw new RuntimeException();
	}
}
