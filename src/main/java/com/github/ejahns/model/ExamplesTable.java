package com.github.ejahns.model;

import java.util.List;
import java.util.stream.Collectors;

import com.github.ejahns.model.interfaces.haselement.HasTableRows;

public class ExamplesTable implements HasTableRows {

	private TableRow header;
	private List<TableRow> tableRows;

	public TableRow getHeader() {
		return header;
	}

	public void setHeader(TableRow header) {
		this.header = header;
	}

	@Override
	public List<TableRow> getTableRows() {
		return tableRows;
	}

	@Override
	public void setTableRows(List<TableRow> tableRows) {
		this.tableRows = tableRows;
	}

	@Override
	public List<List<String>> generateTable() {
		return tableRows.stream().map(TableRow::getCells).collect(Collectors.toList());
	}
}
