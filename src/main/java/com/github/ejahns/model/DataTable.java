package com.github.ejahns.model;

import java.util.ArrayList;
import java.util.List;

import com.github.ejahns.ParserException;
import com.github.ejahns.Token;

//TODO implement
public class DataTable implements GherkinElement {

	private List<List<String>> dataTable = new ArrayList<>();

	@Override
	public boolean add(GherkinElement t) {
		return false;
	}

	@Override
	public boolean consume(Token t) {
		switch (t.getType()) {
			case TableRowToken:
				String line = t.getLine();
				String[] split = line.split("\\|");
				ArrayList<String> row = new ArrayList<>();
				for (int i = 1; i < split.length; i++) {
					row.add(split[i].trim());
				}
				if (dataTable.size() > 0) {
					List<String> lastRow = dataTable.get(dataTable.size() - 1);
					if (lastRow.size() != row.size()) {
						throw new ParserException.UnexpectedTableRowException(line, lastRow.size(), row.size(), t.getLineNum());
					}
				}
				dataTable.add(row);
				return true;
		}
		return false;
	}
}
