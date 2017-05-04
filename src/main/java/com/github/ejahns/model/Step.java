package com.github.ejahns.model;

import com.github.ejahns.Token;

public class Step implements GherkinElement {

	private String step;

	@Override
	public boolean add(GherkinElement t) {
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case StepLineToken:
				this.step = t.getLine();
				return true;
		}
		return false;
	}
}
