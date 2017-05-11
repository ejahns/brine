package com.github.ejahns;

import java.util.ArrayList;

import com.github.ejahns.model.AbstractScenario;
import com.github.ejahns.model.Background;
import com.github.ejahns.model.DataTable;
import com.github.ejahns.model.DocString;
import com.github.ejahns.model.Examples;
import com.github.ejahns.model.ExamplesTable;
import com.github.ejahns.model.Feature;
import com.github.ejahns.model.ScenarioOutline;
import com.github.ejahns.model.Step;
import com.github.ejahns.model.TableRow;
import com.github.ejahns.model.interfaces.GherkinElement;
import com.github.ejahns.model.interfaces.haselement.HasSteps;

class GherkinElementConsumptionHandler {

	public static void consume(GherkinElement e1, GherkinElement e2) {
		if (e1 instanceof HasSteps && e2 instanceof Step) {
			if (null == ((HasSteps) e1).getSteps()) {
				((HasSteps) e1).setSteps(new ArrayList<>());
			}
			((HasSteps) e1).getSteps().add((Step) e2);
			return;
		}
		if (e1 instanceof Feature && e2 instanceof Background) {
			((Feature) e1).setBackground((Background) e2);
			return;
		}
		if (e1 instanceof Feature && e2 instanceof AbstractScenario) {
			if (null == ((Feature) e1).getScenarios()) {
				((Feature) e1).setScenarios(new ArrayList<>());
			}
			((Feature) e1).getScenarios().add((AbstractScenario) e2);
			return;
		}
		if (e1 instanceof DataTable && e2 instanceof TableRow) {
			if (null == ((DataTable) e1).getTableRows()) {
				((DataTable) e1).setTableRows(new ArrayList<>());
			}
			else {
				int size = ((DataTable) e1).getTableRows().get(0).getCells().size();
				if (((TableRow) e2).getCells().size() != size) {
					throw new ParserException.UnexpectedTableRowException((TableRow) e2, size, ((TableRow) e2).getCells().size(), ((TableRow) e2).getLine());
				}
			}
			((DataTable) e1).getTableRows().add((TableRow) e2);
			return;
		}
		if (e1 instanceof Step && e2 instanceof DataTable) {
			((Step) e1).setDataTable((DataTable) e2);
			return;
		}
		if (e1 instanceof Step && e2 instanceof DocString) {
			((Step) e1).setDocString((DocString) e2);
			return;
		}
		if (e1 instanceof ExamplesTable && e2 instanceof TableRow) {
			if (null == ((ExamplesTable) e1).getHeader()) {
				((ExamplesTable) e1).setHeader((TableRow) e2);
				return;
			}
			int size = ((ExamplesTable) e1).getHeader().getCells().size();
			if (((TableRow) e2).getCells().size() != size) {
				throw new ParserException.UnexpectedTableRowException((TableRow) e2, size, ((TableRow) e2).getCells().size(), ((TableRow) e2).getLine());
			}
			if (null == ((ExamplesTable) e1).getTableRows()) {
				((ExamplesTable) e1).setTableRows(new ArrayList<>());
			}
			((ExamplesTable) e1).getTableRows().add((TableRow) e2);
			return;
		}
		if (e1 instanceof Examples && e2 instanceof ExamplesTable) {
			((Examples) e1).setExamplesTable((ExamplesTable) e2);
			return;
		}
		if (e1 instanceof ScenarioOutline && e2 instanceof Examples) {
			if (null == ((ScenarioOutline) e1).getExamples()) {
				((ScenarioOutline) e1).setExamples(new ArrayList<>());
			}
			((ScenarioOutline) e1).getExamples().add((Examples) e2);
			return;
		}
		throw new RuntimeException();
	}
}
