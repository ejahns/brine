package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.Token;

public class Background implements GherkinElement {

	private String backgroundName;
	private List<String> description = new ArrayList<>();
	private List<Step> steps = new ArrayList<>();

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof Step) {
			steps.add((Step) t);
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()){
			case BackgroundLineToken:
				backgroundName = t.getLine();
				return true;
			case OtherToken:
				description.add(t.getLine());
				return true;
		}
		return false;
	}
}
