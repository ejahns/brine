package com.github.ejahns.model.interfaces.haselement;

import java.util.List;

import com.github.ejahns.model.TableRow;
import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasTableRows extends GherkinElement {

	List<TableRow> getTableRows();

	void setTableRows(List<TableRow> tableRows);

	List<List<String>> generateTable();
}
