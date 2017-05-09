package com.github.ejahns.model;

import java.util.List;
import java.util.stream.Collectors;

import com.github.ejahns.model.interfaces.haselement.HasTableRows;

public class DataTable implements HasTableRows {

	private List<TableRow> tableRows;

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
