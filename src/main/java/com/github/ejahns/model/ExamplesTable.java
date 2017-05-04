package com.github.ejahns.model;

import com.github.ejahns.Token;

/**
 * Created by ejahns on 5/3/2017.
 */
public class ExamplesTable implements GherkinElement {

	@Override
	public boolean add(GherkinElement t) {
		return false;
	}

	@Override
	public boolean consume(Token t) {
		return false;
	}
}
