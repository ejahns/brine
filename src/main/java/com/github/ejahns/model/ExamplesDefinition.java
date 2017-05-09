package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.Token;

public class ExamplesDefinition implements GherkinElement {

	private List<String> tags = new ArrayList<>();
	private Examples examples;

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof Examples) {
			this.examples = (Examples) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case TagLineToken:
				this.tags.add(t.getLine());
				return true;
		}
		return false;
	}
}
