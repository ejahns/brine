package com.github.ejahns.model;

import java.util.List;

import com.github.ejahns.model.interfaces.hastoken.HasLineNumber;

public class TableRow implements HasLineNumber {

	private int line;
	private List<String> cells;

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	public List<String> getCells() {
		return cells;
	}

	public void setCells(List<String> cells) {
		this.cells = cells;
	}

	@Override
	public String toString() {
		return cells.toString();
	}
}
