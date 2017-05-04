package com.github.ejahns.model;

import com.github.ejahns.Token;

public interface GherkinElement {

	boolean add(GherkinElement t);

	boolean consume(Token t);
}
