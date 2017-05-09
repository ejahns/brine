package com.github.ejahns.model;

import java.util.List;

import com.github.ejahns.model.interfaces.hastoken.HasDescription;
import com.github.ejahns.model.interfaces.hastoken.HasLineNumber;
import com.github.ejahns.model.interfaces.hastoken.HasName;
import com.github.ejahns.model.interfaces.haselement.HasTags;

public class Examples implements HasLineNumber, HasTags, HasName, HasDescription {

	private int line;
	private List<String> tags;
	private String name;
	private List<String> description;
	private ExamplesTable examplesTable;

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

	public ExamplesTable getExamplesTable() {
		return examplesTable;
	}

	public void setExamplesTable(ExamplesTable examplesTable) {
		this.examplesTable = examplesTable;
	}
}
