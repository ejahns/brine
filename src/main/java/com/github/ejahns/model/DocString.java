package com.github.ejahns.model;

import java.util.List;

import com.github.ejahns.model.interfaces.GherkinElement;

//TODO implement
public class DocString implements GherkinElement {

	private List<String> lines;

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
}
