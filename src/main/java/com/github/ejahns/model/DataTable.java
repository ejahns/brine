package com.github.ejahns.model;

import com.github.ejahns.Token;

public class DataTable implements GherkinElement {

	@Override
	public boolean add(GherkinElement t) {
		return false;
	}

	@Override
	public boolean consume(Token t) {
		return false;
	}
}
