package com.github.ejahns.model;

import java.util.List;

import com.github.ejahns.model.interfaces.hastoken.HasDescription;
import com.github.ejahns.model.interfaces.hastoken.HasLineNumber;
import com.github.ejahns.model.interfaces.hastoken.HasName;
import com.github.ejahns.model.interfaces.haselement.HasTags;

public class Feature implements HasLineNumber, HasTags, HasName, HasDescription {

	private String absoluteLocation;
	private String relativeLocation;

	private int line;
	private List<String> tags;
	private String name;
	private List<String> description;
	private Background background;
	private List<AbstractScenario> scenarios;

	public String getAbsoluteLocation() {
		return absoluteLocation;
	}

	//TODO don't expose this method
	public void setAbsoluteLocation(String absoluteLocation) {
		this.absoluteLocation = absoluteLocation;
	}

	public String getRelativeLocation() {
		return relativeLocation;
	}

	//TODO don't expose this method
	public void setRelativeLocation(String relativeLocation) {
		this.relativeLocation = relativeLocation;
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<String> tags) {
		this.tags = tags;
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

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public List<AbstractScenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<AbstractScenario> scenarios) {
		this.scenarios = scenarios;
	}
}
