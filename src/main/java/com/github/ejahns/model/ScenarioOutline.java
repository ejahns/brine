package com.github.ejahns.model;

import java.util.List;

public class ScenarioOutline extends AbstractScenario {

	private List<Examples> examples;

	public List<Examples> getExamples() {
		return examples;
	}

	public void setExamples(List<Examples> examples) {
		this.examples = examples;
	}
}
