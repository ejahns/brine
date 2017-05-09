package com.github.ejahns.model;

import java.util.List;

import com.github.ejahns.model.interfaces.haselement.HasSteps;
import com.github.ejahns.model.interfaces.hastoken.HasDescription;
import com.github.ejahns.model.interfaces.hastoken.HasLineNumber;
import com.github.ejahns.model.interfaces.hastoken.HasName;

public class Background implements HasLineNumber, HasName, HasDescription, HasSteps {

	private int line;
	private String name;
	private List<String> description;
	private List<Step> steps;

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<String> getDescription() {
		return description;
	}

	@Override
	public void setDescription(List<String> description) {
		this.description = description;
	}

	@Override
	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}
