package com.github.ejahns.model;

import com.github.ejahns.Token;

public class Step implements GherkinElement {

	private int lineNum;
	private String keyword;
	private String step;
	private DataTable dataTable;

	@Override
	public boolean add(GherkinElement t) {
		if (t instanceof DataTable) {
			this.dataTable = (DataTable) t;
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case StepLineToken:
				this.keyword = t.getKeyword();
				this.step = t.getLine();
				this.lineNum = t.getLineNum();
				return true;
		}
		return false;
	}
}
