package com.github.ejahns.model;

import com.github.ejahns.Token;

public class Examples implements GherkinElement {

	private int lineNum;
	private String examplesName;
	private ExamplesTable examplesTable;

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof ExamplesTable) {
			this.examplesTable = (ExamplesTable) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case ExamplesLineToken:
				this.lineNum = t.getLineNum();
				this.examplesName = t.getLine();
				return true;
		}
		return false;
	}
}
