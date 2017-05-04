package com.github.ejahns.model;

import com.github.ejahns.Token;

public class GherkinDocument implements GherkinElement {

	private Feature feature;

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof Feature) {
			feature = (Feature) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case EOFToken:
				return true;
		}
		return false;
	}
}
