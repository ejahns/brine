package com.github.ejahns.model;

import com.github.ejahns.model.interfaces.GherkinElement;

public class Step implements GherkinElement {

	private int line;
	private String keyword;
	private String step;
	private DataTable dataTable;
	private DocString docString;

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	public DocString getDocString() {
		return docString;
	}

	public void setDocString(DocString docString) {
		this.docString = docString;
	}
}
