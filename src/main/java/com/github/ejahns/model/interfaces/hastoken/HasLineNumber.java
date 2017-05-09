package com.github.ejahns.model.interfaces.hastoken;

import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasLineNumber extends GherkinElement {

	int getLine();

	void setLine(int line);
}
